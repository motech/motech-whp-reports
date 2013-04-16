$(function () {
    var filterParams;
    $(document).on("filterUpdated", function (event) {
        filterParams = event.message;
        loadAllContainerRegistrationsCount(filterParams);
        loadContainerRegistrationsByStatus(filterParams);
        renderContainerRegistrationByDistrictChart(filterParams);
    });

    function loadAllContainerRegistrationsCount(filterParams) {
        $.getJSON($('#allContainerRegistrations').data('url') + "&filterParams=" + filterParams, function (data) {
            $("#allContainerRegistrations").html(data.content[0].container_registration_count);
        });
    }

    function loadContainerRegistrationsByStatus(filterParams) {
        $.getJSON($('#containerRegistrationsByStatus').data('url') + "&filterParams=" + filterParams, function (data) {
            var containerStatuses = ich.containerStatuses(data.content[0]);
            $("#containerRegistrationsByStatus").html(containerStatuses);
        });
    }

    function renderContainerRegistrationByDistrictChart(filterParams, hideDistrictWihZeroCountflag) {
        $.getJSON($('#containerRegistrationsByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var adaptedData = extractData(data.content, "district", ["active", "closed"], hideDistrictWihZeroCountflag);
            var chartData = {
                yAxisTitle : 'Number of Container Registrations',
                title : 'Container Registrations By District',
                xAxisTitle: 'Number of Container Registrations',
                xAxis:adaptedData.xAxis,
                series: [ {name:'Active', data:adaptedData.series.active}, {name: 'Closed', data:adaptedData.series.closed}],
                target: 'containerRegistrationsByDistrict'
            }
            renderStackedBarChart(chartData);
        });
    }

    $("#containerRegistrationsByDistrictFlag").click(function() {
        if ($(this).is('input:checked')) {
            renderContainerRegistrationByDistrictChart(filterParams, false);
        } else {
            renderContainerRegistrationByDistrictChart(filterParams, true);
        }
    });

    applyFilter();
})

