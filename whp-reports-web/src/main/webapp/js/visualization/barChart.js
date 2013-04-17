function defaultHighchartsConfig(chartData){
    return {
        chart:{
            type:'bar',
            height: (chartData.xAxis.length * 20 < 200) ? 200 : chartData.xAxis.length * 20
        },
        title:{
            text: chartData.title
        },
        xAxis:{
            categories:chartData.xAxis
        },
        yAxis:{
            min:0,
            title:{
                text:chartData.yAxisTitle,
                align:'high'
            },
            labels:{
                overflow:'justify'
            }
        },
        plotOptions:{
            bar:{
                dataLabels:{
                    enabled:true
                }
            }
        },
        legend:{
            enabled: false
        },
        credits:{
            enabled:false
        },
        series: chartData.series,
        exporting: {
            url: 'http://' + window.location.host + '/highcharts-export/'
        }
    };
}

function renderBarChart(chartData){
    var config = defaultHighchartsConfig(chartData);
    $('#' + chartData.target).highcharts(config);
}

function renderStackedBarChart(chartData){
    var config = defaultHighchartsConfig(chartData);
    config.colors =[ '#D9CF3A', '#89A54E'];
    config.plotOptions = {
        bar:{
            dataLabels:{
                enabled:true
            }
        },
        series: {
            stacking: 'normal'
        },
        candlestick: {
            pointWidth:60
        },
        column: {
            pointWidth:60
        }
    };
    config.legend = { enabled: true };
    $('#' + chartData.target).highcharts(config);

}

function renderColumnChart(chartData){
    var config = defaultHighchartsConfig(chartData);
    config.chart.type = 'column';
    config.legend = { enabled: true };
    $('#' + chartData.target).highcharts(config);
}

