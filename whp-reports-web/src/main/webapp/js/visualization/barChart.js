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
        series:[
            {
                name:chartData.yAxisTitle,
                data:chartData.yAxis
            }
        ]
    });
}

