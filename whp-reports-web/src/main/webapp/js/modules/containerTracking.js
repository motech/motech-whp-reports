$(function () {
    $(document).on("filterUpdated", function(event){
        window.open($('#filter').data('url') + "&filterParams=" + event.message).serialize();
    });
})
