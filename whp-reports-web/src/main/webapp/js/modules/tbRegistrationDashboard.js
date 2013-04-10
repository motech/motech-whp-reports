$(function () {
    $(document).on("filterUpdated", function(event){
        $.getJSON($('#allRegistrations').data('url') + "&filterParams={}", function (data) {
            $("#allRegistrations").html(data.content[0].tb_registration_count);
        });

        $.getJSON($('#closedRegistrations').data('url') + "&filterParams=" + event.message, function (data) {
            var rows = data.content;
            var totalClosedRegistrations = 0;

            $.each(rows, function(index, row) {
                totalClosedRegistrations += row.tb_registration_count;
            });

            var tbOutcomes = ich.tbOutcomes({
                rows:rows
            });

            $("#tbRegistrationsByOutcome").html(tbOutcomes);
            $("#closedRegistrations").html(totalClosedRegistrations);
        });
    });
})