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
            var results = data.content;
            var districts = []
            var tbRegistrationCounts = []

            $.each(results, function (index, row) {
                districts.push(row.district)
                tbRegistrationCounts.push(row.tb_registration_count)
            });


            $('#tbRegistrationsByDistrict').highcharts({
                chart:{
                    type:'bar'
                },
                title:{
                    text:'TB Registrations By District'
                },
                xAxis:{
                    categories:districts
                },
                yAxis:{
                    min:0,
                    title:{
                        text:'Number of TB Registrations',
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
                        name:'Number of TB Registrations',
                        data:tbRegistrationCounts
                    }
                ]
            });
        });
    }

    function renderProvidersByDistrictChart(filterParams) {
        $.getJSON($('#providersByDistrict').data('url') + "&filterParams=" + filterParams, function (data) {
            var results = data.content;
            var districts = []
            var providerCounts = []

            $.each(results, function (index, row) {
                districts.push(row.district)
                providerCounts.push(row.provider_count)
            });


            $('#providersByDistrict').highcharts({
                chart:{
                    type:'bar'
                },
                title:{
                    text:'TB Registrations By Provider per District'
                },
                xAxis:{
                    categories:districts
                },
                yAxis:{
                    min:0,
                    title:{
                        text:'Number of Providers',
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
                        name:'Number of TB Registrations',
                        data:providerCounts
                    }
                ]
            });
        });
    }
    applyFilter();
})

