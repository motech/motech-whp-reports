
function extractData(results, xAxis, yAxis){
    xAxisData=[]
    yAxisData=[]

    $.each(results, function (index, row) {
        xAxisData.push(row[xAxis])
        yAxisData.push(row[yAxis])
    });

    return {
        xAxis:xAxisData,
        yAxis:yAxisData
    }
}