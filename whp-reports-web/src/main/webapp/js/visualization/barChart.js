function renderBarChart(chartData){
    $('#' + chartData.target).highcharts({
        chart:{
            type:'bar'
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
        series: chartData.series
    });
}

function renderStackedBarChart(chartData){
    $('#' + chartData.target).highcharts({
        colors: [
            '#D9CF3A',
            '#89A54E'],
        chart:{
            type:'bar'
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
        },
        legend:{
            enabled: true
        },
        credits:{
            enabled:false
        },
        series: chartData.series
    });
}

