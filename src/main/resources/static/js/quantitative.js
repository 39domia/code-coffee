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
            $('#productNameQuantitative').append(
                `
                <option value="" selected disabled hidden>- Chọn sản phẩm -</option>
                `
            );
            $.each(data, function (i, v) {
                $('#productNameQuantitative').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            });
            $('#ingredientNameQuantitative').empty();
            $('#ingredientNameQuantitative').append(
                `
                <option value="" selected disabled hidden>- Chọn nguyên liệu -</option>
                `
            );
        }
    });
};

quantitative.addNew = function () {
    $('#formAddEditQuantitative')[0].reset();
    $('#modalTitleQuantitative').html("Thêm định mức sản phẩm mới");
    quantitative.resetForm();
    $('#productNameQuantitative').attr("disabled", false);
    $('#ingredientNameQuantitative').attr("disabled", true);
    $('#modalAddEditQuantitative').modal('show');
}

quantitative.resetForm = function () {
    $('#formAddEditQuantitative')[0].reset();
};

quantitative.selectProductRenderIngredients = function () {
    let idProduct = $('#productNameQuantitative').val();
    $.ajax({
        url: `${apiUrl}/ingredients/not-quantitative-product/${idProduct}`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#ingredientNameQuantitative').attr("disabled", false);
            $('#ingredientNameQuantitative').empty();
            $('#ingredientNameQuantitative').append(
                `
                <option value="" selected disabled hidden>- Chọn nguyên liệu -</option>
                `
            );
            $.each(data, function (i, v) {
                $('#ingredientNameQuantitative').append(
                    `
                    <option value="${v.id}">${v.name}</option>
                    `
                );
            });
        },
        error: function () {
            toastr.error("Lỗi tìm sản phẩm");
        }
    })
};


quantitative.findProductById = function (idProduct, quantitativeAddObj) {
    $.ajax({
        url: `${apiUrl}/products/${idProduct}`,
        async: false,
        method: "GET",
        dataType: "json",
        success: function (data) {
            quantitativeAddObj.product = data;
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

quantitative.get = function (idProduct, idIngredient) {
    $.ajax({
        url: `${apiUrl}/quantitatives/product/${idProduct}/ingredient/${idIngredient}/`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEditQuantitative')[0].reset();
            $('#modalTitleQuantitative').html("Sửa định mức");
            $('#productNameQuantitative').attr("disabled", true);
            $('#ingredientNameQuantitative').attr("disabled", true);
            $('#productIdQuantitative').val(data.product.id);
            $('#ingredientIdQuantitative').val(data.ingredient.id);
            $('#productNameQuantitative option:selected').html(data.product.name);
            $('#ingredientNameQuantitative option:selected').html(data.ingredient.name);
            $('#quantityQuantitative').val(data.quantity);
            $('#modalAddEditQuantitative').modal('show');
        }
    });
};


quantitative.addOrUpdate = function () {
    if ($("#formAddEditQuantitative")) {
        if ($('#productIdQuantitative').val() && $('#ingredientIdQuantitative').val()) {
            let quantitativeAddObj = {};
            quantitative.findProductById(Number($('#productIdQuantitative').val()), quantitativeAddObj);
            quantitative.findIngredientById(Number($('#ingredientIdQuantitative').val()), quantitativeAddObj);
            quantitativeAddObj.quantity = Number($('#quantityQuantitative').val());
            console.log(quantitativeAddObj);
            $.ajax({
                url: `${apiUrl}/quantitatives/product/${quantitativeAddObj.product.id}/ingredient/${quantitativeAddObj.ingredient.id}`,
                method: "PUT",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(quantitativeAddObj),
                success: function () {
                    toastr.success("Cập nhật thành công");
                    $('#modalAddEditQuantitative').modal('hide');
                    $("#quantitative-datatables").DataTable().ajax.reload();
                },
                error: function () {
                    toastr.error("Lỗi cập nhật định mức");
                }
            });
        } else {
            let quantitativeAddObj = {};
            quantitative.findProductById(Number($('#productNameQuantitative').val()), quantitativeAddObj);
            quantitative.findIngredientById(Number($('#ingredientNameQuantitative').val()), quantitativeAddObj);
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
        }
    }
}

quantitative.delete = function (idProduct, idIngredient) {
    bootbox.confirm({
        title: "Xóa định mức này?",
        message: "Xóa định mức này?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Nghĩ lại'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Có'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: `${apiUrl}/quantitatives/product/${idProduct}/ingredient/${idIngredient}`,
                    method: "DELETE",
                    dataType: "json",
                    success: function () {
                        Command: toastr["success"]("Xóa định mức thành công");
                        toastr.options = {
                            "closeButton": false,
                            "debug": false,
                            "newestOnTop": false,
                            "progressBar": true,
                            "positionClass": "toast-top-right",
                            "preventDuplicates": false,
                            "onclick": null,
                            "showDuration": "300",
                            "hideDuration": "1000",
                            "timeOut": "2000",
                            "extendedTimeOut": "1000",
                            "showEasing": "swing",
                            "hideEasing": "linear",
                            "showMethod": "fadeIn",
                            "hideMethod": "fadeOut"
                        };
                        $('#modalAddEditQuantitative').modal('hide');
                        $("#quantitative-datatables").DataTable().ajax.reload();
                    },
                    error: function () {
                        toastr.error("Lỗi xóa định mức");
                    }
                });
            }
        }
    })
}

quantitative.init = function () {
    quantitative.initQuantitativeTable();
    quantitative.initProductsAndIngredients();
};


$(document).ready(function () {
    quantitative.init();
});