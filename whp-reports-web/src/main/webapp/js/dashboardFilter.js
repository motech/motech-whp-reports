$(function () {

    $("#from_date").datepicker({
        dateFormat:'dd/mm/yy',
        onClose:function (selectedDate) {
            $("#to_date").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#to_date").datepicker({
        dateFormat:'dd/mm/yy',
        onClose:function (selectedDate) {
            $("#from_date").datepicker("option", "maxDate", selectedDate);
        }
    });

    getDate = function (dtString, deltaDays) {
        var dateParts = dtString.split("/");
        var newDate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
        newDate.setDate(parseInt(dateParts[0]) + deltaDays)

        return newDate;
    }

    $(".show-date-button").click(function () {
        $(this).parent().find(".dates").focus();
    });

    $(".clear-date-button").click(function () {
        var dateElement = $(this).parent().find(".dates");
        dateElement.val("");

        var relatedDateField = $("#" + dateElement.data("date-range"));
        relatedDateField.datepicker("option", "minDate", "");
        relatedDateField.datepicker("option", "maxDate", "");
    });


    function applyFilter(){
        var dataString = JSON.stringify($("#dashboardFilter").serializeJSON());
        $.event.trigger({
            type: "filterUpdated",
            message: dataString
        });
    }

    $('#dashboardFilter').bind('click', function (event) {
        applyFilter();
    });

    applyFilter();
});