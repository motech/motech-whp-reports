$(function () {
    $(document).on("filterUpdated", function (event) {
        var filterParams = event.message;
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

            var tbOutcomes = ich.tbOutcomes({
                rows:rows
            });

            $("#tbRegistrationsByOutcome").html(tbOutcomes);
            $("#closedRegistrations").html(totalClosedRegistrations);
        });
    }

    function loadAllTbRegistrationCounts(filterParams) {
        $.getJSON($('#allRegistrations').data('url') + "&filterParams=" + filterParams, function (data) {
            $("#allRegistrations").html(data.content[0].tb_registration_count);
        });
    }

    function renderTbRegistrationByDistrictChart(filterParams) {
        $.getJSON($('#tbRegistrationsByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var adaptedData = extractData(data.content, "district", "tb_registration_count");
            var chartData = {
                yAxisTitle : 'Number of TB Registrations',
                title : 'TB Registrations By District',
                xAxisTitle: 'Number of TB Registrations',
                xAxis:adaptedData.xAxis,
                yAxis:adaptedData.yAxis,
                target: 'tbRegistrationsByDistrict'
            }
            renderBarChart(chartData);
        });
    }

    function renderProvidersByDistrictChart(filterParams) {
        $.getJSON($('#providersByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var adaptedData = extractData(data.content, "district", "provider_count");
            var chartData= {
                yAxisTitle :'Number of Providers',
                title : 'Providers per District',
                xAxisTitle: 'Number of Providers',
                xAxis:adaptedData.xAxis,
                yAxis:adaptedData.yAxis,
                target: 'providersByDistrict'
            }
            renderBarChart(chartData);
        });
    }
    applyFilter();
})

