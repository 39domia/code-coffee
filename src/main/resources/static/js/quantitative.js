let quantitative = {};

quantitative.initQuantitativeTable = function () {
    $("#quantitative-datatables").DataTable({
        ajax: {
            url: `${apiUrl}/quantitatives/`,
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "product.name", name: "product.name", title: "Tên sản phẩm", orderable: true,
            },
            {
                data: "ingredient.name", name: "ingredient.name", title: "Tên nguyên liệu",
            },
            {data: "quantity", name: "quantity", title: "Số lượng"},
            {
                title: "Chức năng", orderable: false,
                "render": function (data, type, row) {
                    return `
                    <a class='mr-2' href='javascript:;' title='Chỉnh sửa' onclick='quantitative.get(${row.product.id}, ${row.ingredient.id})'><i class='fa fa-edit'></i></a> 
                    <a class='mr-2' href='javascript:;' title='Xóa' onclick='quantitative.delete(${row.product.id}, ${row.ingredient.id})' ><i class='fa fa-trash'></i></a>
                    <a class='mr-2' href='javascript:;' title='Thông tin chi tiết' onclick='quantitative.viewProduct(${row.product.id}, ${row.ingredient.id})' ><i class='fas fa-eye'></i></a>
                    `
                }
            },
        ],
        language: {
            "sProcessing": "Đang xử lý...",
            "sLengthMenu": "Xem _MENU_ mục",
            "sZeroRecords": "Không tìm thấy dòng nào phù hợp",
            "sInfo": "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",
            "sInfoEmpty": "Đang xem 0 đến 0 trong tổng số 0 mục",
            "sInfoFiltered": "(được lọc từ _MAX_ mục)",
            "sSearch": "Tìm kiếm:",
            "oPaginate": {
                "sFirst": "Đầu",
                "sPrevious": "Trước",
                "sNext": "Tiếp",
                "sLast": "Cuối"
            }
        }
    });
}

quantitative.initProductsAndIngredients = function () {
    $.ajax({
        url: `${apiUrl}/products/isIngredient`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#productNameQuantitative').empty();
            $.each(data, function (i, v) {
                $('#productNameQuantitative').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            });
        }
    });
    $.ajax({
        url: `${apiUrl}/ingredients`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#ingredientNameQuantitative').empty();
            $.each(data, function (i, v) {
                $('#ingredientNameQuantitative').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            });
        }
    });
};

quantitative.addNew = function () {
    $('#formAddEditQuantitative')[0].reset();
    $('#modalTitleQuantitative').html("Thêm định mức sản phẩm mới");
    quantitative.resetForm();
    $('#modalAddEditQuantitative').modal('show')
}

quantitative.resetForm = function () {
    $('#formAddEditQuantitative')[0].reset();
};

quantitative.findProductById = function (idProduct,quantitativeAddObj) {
    $.ajax({
        url: `${apiUrl}/products/${idProduct}`,
        async: false,
        method: "GET",
        dataType: "json",
        success: function (data) {
            quantitativeAddObj.product = data;
            console.log(quantitativeAddObj);
        },
        error: function () {
            toastr.error("Lỗi tìm sản phẩm");
        }
    });
};

quantitative.findIngredientById = function (idIngredient, quantitativeAddObj) {
    $.ajax({
        url: `${apiUrl}/ingredients/${idIngredient}`,
        async: false,
        method: "GET",
        dataType: "json",
        success: function (data) {
            quantitativeAddObj.ingredient = data;
        },
        error: function () {
            toastr.error("Lỗi tìm nguyên liệu");
        }
    });
};


quantitative.save = function (){
    if ($("#formAddEditQuantitative")) {
        if (!$('#id').val()) {
            let quantitativeAddObj = {};
            quantitative.findProductById(Number($('#productNameQuantitative').val()), quantitativeAddObj);
            quantitative.findIngredientById(Number($('#ingredientNameQuantitative').val()),quantitativeAddObj);
            quantitativeAddObj.quantity = Number($('#quantityQuantitative').val());
            console.log(quantitativeAddObj);
            $.ajax({
                url: `${apiUrl}/quantitatives/`,
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(quantitativeAddObj),
                success: function () {
                    toastr.success("Thêm mới thành công");
                    $('#modalAddEditQuantitative').modal('hide');
                    $("#quantitative-datatables").DataTable().ajax.reload();
                },
                error: function () {
                    toastr.error("Lỗi thêm định mức");
                }
            });
        } else {
            let productObj = {};
            productObj.id = Number($('#id').val());
            productObj.name = $('#productName').val();
            productObj.price = Number($('#price').val());
            productObj.image = $('#imgUrl').val();
            productObj.ingredient = $('#isIngredient').val();
            productObj.inventory = products.setInventory(productObj);
            productObj.productStatus = products.setProductStatus(productObj.inventory, productObj);

            let productLineObj = {};
            productLineObj.id = Number($('#productLine').val());
            productLineObj.name = $("#productLine option:selected").html();
            productObj.productLine = productLineObj;
            console.log(productObj);

            $.ajax({
                url: `${apiUrl}/products/${productObj.id}`,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(productObj),
                success: function (data) {
                    toastr.success("Cập nhật thành công")
                    $('#modalAddEdit').modal('hide');
                    $("#products-datatables").DataTable().ajax.reload();
                    productExports.update(productObj.name,productObj.id);
                    billDetails.update(productObj.name,productObj.id);
                },
                error: function () {
                    console.log('loi update')
                }
            });
        }
    }

}

// quantitative.get = function (idProduct, idIngredient) {
//     $.ajax({
//         url: `${apiUrl}/quantitatives/product/${idProduct}/ingredient/${idIngredient}/`,
//         method: "GET",
//         dataType: "json",
//         success: function (data) {
//             console.log(data);
//             $('#formAddEditQuantitative')[0].reset();
//             $('#modalTitleQuantitative').html("Sửa định mức");
//             $('#productName').val(data.name);
//             $('#inventory').val(data.inventory);
//             $('#done-upload').html(
//                 `<img src="${data.image}" style="max-width: 300px; max-height: 300px; width: 100%; height: 100%" alt="Ảnh sản phẩm" class="img-thumbnail">`
//             );
//             $('#price').val(data.price);
//             $('#imgUrl').val(data.image);
//             $('#multiImage').val(data.multiImage);
//             $('#productLine').val(data.productLine.id);
//             $('#productStatus').val(data.productStatus);
//             $('#isIngredient option:selected').html(quantitative.renderIsIngredient(data));
//             $('#isIngredient').prop("disabled", true);
//             $('#id').val(data.id);
//             $("#save-btn").html(
//                 `<a href="javascript:;" class="btn btn-primary"
//                                onclick="quantitative.save()">Lưu</a>`
//             );
//             $('#modalAddEdit').modal('show');
//         }
//     });
// };

quantitative.init = function () {
    quantitative.initQuantitativeTable();
    quantitative.initProductsAndIngredients();
};


$(document).ready(function () {
    quantitative.init();
});