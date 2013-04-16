
function extractData(results, xAxis, series, hideRowsWithZeroCount){

    if(typeof(hideRowsWithZeroCount)==='undefined') hideRowsWithZeroCount = true;

    xAxisData=[]
    seriesData = {}

    $.each(series, function (index, value){
        seriesData[value] = []
    });

    $.each(results, function (index, row) {
        var sum = 0;
        xAxisData.push(row[xAxis])
        $.each(series, function (index, value){
            seriesData[value].push(row[value])
            sum += row[value];
        });

        if(hideRowsWithZeroCount && sum == 0) {
            xAxisData.pop()
            $.each(series, function (index, value){
                seriesData[value].pop()
            });
        }
    });

    return {
        xAxis:xAxisData,
        series:seriesData
    }
}
