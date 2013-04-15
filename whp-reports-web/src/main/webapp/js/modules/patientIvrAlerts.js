$(function () {
    $.getJSON($('#alertEffectiveness').data('url'), function(data) {
        var adaptedData = extractData(data.content, "call_week_end_date", ["patient_with_ivr_calls", "patients_with_adherence_given"]);
        var chartData= {
            yAxisTitle :'Patient IVR Alerts',
            title : 'Patient IVR Alerts Effectiveness',
            xAxisTitle: 'Weekending Date',
            xAxis:adaptedData.xAxis,
            series: [ {name:'Alerted Patients', data:adaptedData.series.patient_with_ivr_calls},
                      {name:'Adherence Reported After Call', data:adaptedData.series.patients_with_adherence_given}],
            target: 'alertEffectiveness'
        }
        renderColumnChart(chartData);
    });
});