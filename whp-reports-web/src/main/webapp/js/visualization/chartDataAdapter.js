
function extractData(results, xAxis, series){
    xAxisData=[]
    seriesData = {}

    $.each(series, function (index, value){
        seriesData[value] = []
    });

    $.each(results, function (index, row) {
        xAxisData.push(row[xAxis])
        $.each(series, function (index, value){
            seriesData[value].push(row[value])
        });
    });

    return {
        xAxis:xAxisData,
        series:seriesData
    }
}