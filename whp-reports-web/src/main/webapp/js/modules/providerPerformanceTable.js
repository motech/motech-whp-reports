$(function () {
    $.getJSON($('#providerPerformance').data('url'), function(data) {
        var rows = data.content;

        var directives = {
            zeroProviderPercentage: {
                text: function(params) {
                    return Math.round(100*this.zero_week_bucket/(this.zero_week_bucket + this.two_week_bucket + this.three_to_five_week_bucket + this.six_to_eight_week_bucket));
                }
            },

            twoWeekProviderPercentage: {
                text: function(params) {
                    return Math.round(100*this.two_week_bucket/(this.zero_week_bucket + this.two_week_bucket + this.three_to_five_week_bucket + this.six_to_eight_week_bucket));
                }
            },

            fiveWeekProviderPercentage: {
                text: function(params) {
                    return Math.round(100*this.three_to_five_week_bucket/(this.zero_week_bucket + this.two_week_bucket + this.three_to_five_week_bucket + this.six_to_eight_week_bucket));
                }
            },

            eightWeekProviderPercentage: {
                text: function(params) {
                    return Math.round(100*this.six_to_eight_week_bucket/(this.zero_week_bucket + this.two_week_bucket + this.three_to_five_week_bucket + this.six_to_eight_week_bucket));
                }
            }
        };

        $('#providerPerformances').render(rows, directives);
    });
});