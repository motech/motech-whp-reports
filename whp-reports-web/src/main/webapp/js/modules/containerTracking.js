$(function () {
    $(document).on("filterUpdated", function(event){
        window.open($('#containerTracking').data('url') + "?filterParams=" + event.message);
    });
})
