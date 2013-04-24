$(function () {
    $.getJSON($('#printProviderPerformance').data('url'), function(data) {
        var rows = data.content;
        $('#providerPerformances').render(rows);
        window.print();
    });

});