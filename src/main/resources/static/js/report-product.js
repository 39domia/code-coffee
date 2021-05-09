let reportProducts = {};

reportProducts.showDatatable = function () {
    $('#report-product').empty().append(
        `<h1 class="h3 mb-2 text-gray-800">Báo cáo sản phẩm</h1>
         <p class="mb-4"></p>
         <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary text-uppercase mb-2">Báo cáo sản phẩm</h6>
                        <p class="mb-1">Lịch sử chi tiết các sản phẩm đã được bán ra theo thời điểm. Mặc định hiển thị danh sách trong ngày.</p>
                    </div>
                     <div class="card-body">
                        <div class="d-sm-flex">
                            <div class="media align-items-center">
                                <div class="bg-primary d-flex align-items-center justify-content-center rounded px-3 py-4 text-white mr-3">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                         fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                         stroke-linejoin="round">
                                        <line x1="12" y1="1" x2="12" y2="23"></line>
                                        <path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"></path>
                                    </svg>
                                </div>
                                <div class="media-body"><h6
                                        class="tx-uppercase text-muted">
                                    Tổng Doanh Thu</h6>
                                    <h4 class="" id="total-product"></h4></div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body bd-t">
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
                                    <span class="text" onclick="reportProducts.showProductExport()">Áp dụng</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body bd-t">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dateTableBills" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th colspan="3">Sản phẩm</th>
                                    <th style="text-align: right">Số lượng</th>
                                    <th style="text-align: right">Giá</th>
                                </tr>
                                </thead>
                                <tbody id="list-productExport">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>`
    )
    setDatetime();
    reportProducts.showProductExport();
};

reportProducts.showProductExport = function () {
    let sum = 0;
    let dateObj = {};
    dateObj.dateIn = $('#test-time1').val();
    dateObj.dateOut = $('#test-time2').val();
    console.log(dateObj);
    $.ajax({
        url: `${apiUrl}/productExports/dateExport`,
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(dateObj),
        success: function (data) {
            console.log(data);
            $('#list-productExport').empty();
            $.each(data, function (i, v) {
                sum += v.quantity * v.priceEach;
                $('#list-productExport').append(
                    `<tr>
                        <td colspan="3">${v.nameProduct}</td>
                        <td style="text-align: right">${v.quantity}</td>
                        <td style="text-align: right">${v.quantity * v.priceEach}</td>
                    </tr>`
                )
            })
            $('#total-product').html(`${sum} ₫`);
        }
    })
}

$(document).ready(function () {
    reportProducts.showDatatable();
});

