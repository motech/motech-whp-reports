$(function () {
    var filterParams;
    $(document).on("filterUpdated", function (event) {
        filterParams = event.message;
        loadAllTbRegistrationCounts(filterParams);
        loadClosedTbRegistrationCounts(filterParams)
        renderTbRegistrationByDistrictChart(filterParams);
        renderProvidersByDistrictChart(filterParams);
    });

    function loadClosedTbRegistrationCounts(filterParams) {
        $.getJSON($('#closedRegistrations').data('url') + "&filterParams=" + filterParams, function (data) {
            var rows = data.content;
            var totalClosedRegistrations = 0;
            $.each(rows, function (index, row) {
                totalClosedRegistrations += row.tb_registration_count;
            });

            $('#tbOutcomes').render(rows);
            $("#closedRegistrations").html(totalClosedRegistrations);
        });
    }

    function loadAllTbRegistrationCounts(filterParams) {
        $.getJSON($('#allRegistrations').data('url') + "&filterParams=" + filterParams, function (data) {
            $("#allRegistrations").html(data.content[0].tb_registration_count);
        });
    }

    function renderTbRegistrationByDistrictChart(filterParams, hideDistrictWihZeroCountflag) {
        $.getJSON($('#tbRegistrationsByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var adaptedData = extractData(data.content, "district", ["tb_registration_count"], hideDistrictWihZeroCountflag);
            var chartData = {
                yAxisTitle : 'Number of TB Registrations',
                title : 'TB Registrations By District',
                xAxisTitle: 'Number of TB Registrations',
                xAxis:adaptedData.xAxis,
                series: [ {name:'Number of TB Registrations', data:adaptedData.series.tb_registration_count}],
                target: 'tbRegistrationsByDistrict'
            }
            renderBarChart(chartData);
        });
    }

    function renderProvidersByDistrictChart(filterParams, hideDistrictWihZeroCountflag) {
        $.getJSON($('#providersByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var adaptedData = extractData(data.content, "district", ["provider_count"], hideDistrictWihZeroCountflag);
            var chartData= {
                yAxisTitle :'Number of Providers',
                title : 'Providers per District',
                xAxisTitle: 'Number of Providers',
                xAxis:adaptedData.xAxis,
                series: [ {name:'Number of Providers', data:adaptedData.series.provider_count}],
                yAxis:adaptedData.series.provider_count,
                target: 'providersByDistrict'
            }
            renderBarChart(chartData);
        });
    }

    $("#tbRegistrationsByDistrictFlag").click(function() {
        if ($(this).is('input:checked')) {
            renderTbRegistrationByDistrictChart(filterParams, false);
        } else {
            renderTbRegistrationByDistrictChart(filterParams, true);
        }
    });

    $("#providersByDistrictFlag").click(function() {
        if ($(this).is('input:checked')) {
            renderProvidersByDistrictChart(filterParams, false);
        } else {
            renderProvidersByDistrictChart(filterParams, true);
        }
    });

    applyFilter();
})

