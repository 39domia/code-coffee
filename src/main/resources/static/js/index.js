let dashboard = {};

dashboard.pickMonth = function () {
// let monthPicked = $('#time-select-1').val();
    let dayOfMonth = moment(`${moment().year()}-${moment().month() + 1}`, "YYYY-MM").daysInMonth();
    $('#datetime-hidden-1').val(`${moment().year()}-${moment().month() + 1}-1 00:00:00`);
    $('#datetime-hidden-2').val(`${moment().year()}-${moment().month() + 1}-${dayOfMonth} 23:59:59`);
// console.log(`crt ${monthPicked}`);
}

dashboard.showEarningMonthly = function () {
    let total = 0;
    let dateObj = {};
    dateObj.dateIn = $('#datetime-hidden-1').val();
    dateObj.dateOut = $('#datetime-hidden-2').val();
    $.ajax({
        url: `${apiUrl}/bills/dateExport`,
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(dateObj),
        success: function (data) {
            $.each(data, function (i, v) {
                total += v.totalPrice;
            })
            $('#revenue-current-month').html(`${formatPrice(total)}`);
        }
    })
}
dashboard.showTopSell = function () {
    $.ajax({
        url: `${apiUrl}/billDetails/topSell`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#top-sell-table').empty();
            $.each(data, function (i, v) {
                $('#top-sell-table').append(
                    `
                    <tr>
                        <td>${v.nameProduct}</td>
                        <td>${v.quantity}</td>
                    </tr> 
                    `
                )
            })
        }
    })
}
dashboard.showEarningMonthlyBar = function () {

    // Themes begin
    am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
    var chart = am4core.create("revenue-chart", am4charts.XYChart);
    chart.scrollbarX = new am4core.Scrollbar();


    for (let i = 0; i < 12; i++) {
        let dayOfMonth = moment(`${moment().year()}-${i + 1}`, "YYYY-MM").daysInMonth();
        let startDay = `${moment().year()}-${i + 1}-1 00:00:00`;
        let endDay = `${moment().year()}-${i + 1}-${dayOfMonth} 23:59:59`;
        let dateObj = {};
        dateObj.dateIn = startDay;
        dateObj.dateOut = endDay;
        $.ajax({
            url: `${apiUrl}/bills/dateExport`,
            async: false,
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(dateObj),
            success: function (data) {
                let total = 0;
                $.each(data, function (i, v) {
                    total += v.totalPrice;
                })
                chart.data.push({
                    country: `Tháng ${i + 1}`,
                    visits: total
                });
            }
        })
    }


    // Create axes
    var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
    categoryAxis.dataFields.category = "country";
    categoryAxis.renderer.grid.template.location = 0;
    categoryAxis.renderer.minGridDistance = 30;
    categoryAxis.renderer.labels.template.horizontalCenter = "right";
    categoryAxis.renderer.labels.template.verticalCenter = "middle";
    categoryAxis.renderer.labels.template.rotation = 270;
    categoryAxis.tooltip.disabled = true;
    categoryAxis.renderer.minHeight = 110;

    var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
    valueAxis.renderer.minWidth = 50;

// Create series
    var series = chart.series.push(new am4charts.ColumnSeries());
    series.sequencedInterpolation = true;
    series.dataFields.valueY = "visits";
    series.dataFields.categoryX = "country";
    series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
    series.columns.template.strokeWidth = 0;

    series.tooltip.pointerOrientation = "vertical";

    series.columns.template.column.cornerRadiusTopLeft = 10;
    series.columns.template.column.cornerRadiusTopRight = 10;
    series.columns.template.column.fillOpacity = 0.8;

// on hover, make corner radiuses bigger
    var hoverState = series.columns.template.column.states.create("hover");
    hoverState.properties.cornerRadiusTopLeft = 0;
    hoverState.properties.cornerRadiusTopRight = 0;
    hoverState.properties.fillOpacity = 1;

    series.columns.template.adapter.add("fill", function (fill, target) {
        return chart.colors.getIndex(target.dataItem.index);
    });

// Cursor
    chart.cursor = new am4charts.XYCursor();
}
dashboard.showTotalRevenue = function () {
    $.ajax({
        url: `${apiUrl}/bills/totalRevenue`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#revenue-all').html(`${formatPrice(data.totalPrice)}`);
        }
    })
}
dashboard.showCompletedBills = function () {
    $.ajax({
        url: `${apiUrl}/bills/countBills`,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#completed-bills').html(data.totalPrice);
        }
    })
}

function cache_clear() {
    window.location.reload(true)
}

$(document).ready(function () {
    setInterval(function () {
        dashboard.showTopSell();
        dashboard.pickMonth();
        dashboard.showEarningMonthly();
        dashboard.showTotalRevenue();
        dashboard.showCompletedBills();
    },3000);
    setInterval(function (){
        dashboard.showEarningMonthlyBar();
    },20000)
    dashboard.showTopSell();
    dashboard.pickMonth();
    dashboard.showEarningMonthly();
    dashboard.showTotalRevenue();
    dashboard.showCompletedBills();
    dashboard.showEarningMonthlyBar();
});