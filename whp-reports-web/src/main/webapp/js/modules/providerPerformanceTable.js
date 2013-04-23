$(function () {
    $.getJSON($('#providerPerformance').data('url'), function(data) {
        var rows = data.content;
        $('#providerPerformances').render(rows);
    });
});