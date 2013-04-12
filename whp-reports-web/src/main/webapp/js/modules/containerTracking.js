$(function () {
    $(document).on("filterUpdated", function(event){
        window.open($('#containerTracking').data('url') + "?" + $.param(JSON.parse(event.message)));
    });
})
