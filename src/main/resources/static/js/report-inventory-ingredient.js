let reportInventoryIngredients = {};

reportInventoryIngredients.showDatatable = function () {
    $('#report-inventory-ingredient').empty().append(
        `<h1 class="h3 mb-2 text-gray-800">Báo cáo tồn kho</h1>
         <p class="mb-4"></p>
         <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary text-uppercase">Báo cáo tồn kho</h6>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                        <label class="col-form-label mr-2">Từ ngày</label>
                                        <input type="text" id="test-time1" class="form-control datetimepicker-input"
                                               data-target="#datetimepicker1"/>
                                        <div class="input-group-append" data-target="#datetimepicker1"
                                             data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
                                        <label class="col-form-label mr-2">Đến ngày</label>
                                        <input type="text" id="test-time2" class="form-control datetimepicker-input"
                                               data-target="#datetimepicker2"/>
                                        <div class="input-group-append" data-target="#datetimepicker2"
                                             data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <a href="javascript:;" class="btn btn-light btn-icon-split border">
                                    <span class="icon text-gray-600 bg-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                             stroke-linecap="round" stroke-linejoin="round"><path
                                                d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline
                                                points="22 4 12 14.01 9 11.01"></polyline></svg>
                                    </span>
                                    <span class="text" onclick="reportInventoryIngredients.showInventory()">Áp dụng</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body bd-t">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dateTableBills" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th colspan="3">Nguyên liệu</th>
                                    <th >Tồn kho trước</th>
                                    <th >Số hàng xuất</th>
                                    <th >Tồn kho sau</th>
                                </tr>
                                </thead>
                                <tbody id="list-ingredientInventory">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>`
    )
    setDatetime();
    reportInventoryIngredients.showInventory();
}

reportInventoryIngredients.showInventory = function () {
    let dateObj = {};
    dateObj.dateIn = $('#test-time1').val();
    dateObj.dateOut = $('#test-time2').val();
    console.log(dateObj);
    $.ajax({
        url: `${apiUrl}/quantitativeInventories/dateExport`,
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(dateObj),
        success: function (data) {
            console.log(data);
            $('#list-ingredientExport').empty();
            $.each(data, function (i, v) {
                $('#list-ingredientInventory').append(
                    `<tr>
                        <td colspan="3">${v.nameIngredient}</td>
                        <td >${v.importIngredient}</td>
                        <td >${v.exportIngredient}</td>
                        <td >${v.importIngredient - v.exportIngredient}</td>
                    </tr>`
                )
            })
        }
    })
}

$(document).ready(function () {
    reportInventoryIngredients.showDatatable();
});