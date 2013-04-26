$(function () {
    $.getJSON($('#whpStatistics').data('url'), function(data) {
        var rows = data.content;
        var whpStats = {}
        $.map( rows, function(val, i) {
            whpStats[val.subject] = val.count;
        });
        $('#whpStatsResults').render(whpStats);
    });
});