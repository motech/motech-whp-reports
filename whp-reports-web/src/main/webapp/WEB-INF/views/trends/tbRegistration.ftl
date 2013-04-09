<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="TB Registration Trends">
<h1>TB Registration Trends</h1>

<div>
    <form name="reportsFilter" id ="reportsFilter">
        <div class="row-fluid">
            <div class="well">
                <fieldset class="filters">
                    <div class="controls span3">
                        <label class="control-label">District</label>

                        <div class="controls">
                            <select id="district" name="district">
                                <option value="">All</option>
                                <#list districts as district>
                                    <option value="${district.name}">${district.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="controls span2">
                        <label class="control-label  date-field">Date From</label>

                        <div class="input-append input-prepend">
                            <span class="add-on show-date-button"><i class="icon-calendar"></i></span>
                            <input class="dates" type="text" placeholder="From: dd/mm/yyyy"
                                   data-date-format="dd/mm/yyyy" id="from_date" name="from_date"
                                   data-date-range="to_date">
                            <span class="add-on clear-date-button"><i class="icon-remove-sign"></i></span>
                        </div>
                    </div>

                    <div class="controls span2">
                        <label class="control-label  date-field">Date To</label>

                        <div class="input-append input-prepend">
                            <span class="add-on show-date-button"><i class="icon-calendar"></i></span>
                            <input class="dates" type="text" placeholder="To: dd/mm/yyyy"
                                   data-date-format="dd/mm/yyyy"
                                   id="to_date" name="to_date" data-date-range="from_date">
                            <span class="add-on clear-date-button"><i class="icon-remove-sign"></i></span>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
        <div class="control-group buttons-group row-fluid">
            <div class="controls pull-left">
                <button id="clearFilter" type="reset" class="btn">Clear All</button>
                <button type="button" id="filter" class="btn btn-primary">Filter
                    <i class="icon-download icon-white"></i></button>
            </div>
        </div>
    </form>
    <div id = "allRegistrations"></div>
</div>

<script type="text/javascript">
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

        $('#filter').bind('click', function (event) {
            var dataString = JSON.stringify($("#reportsFilter").serializeJSON());
            $.getJSON("<@spring.url '/bigquery/execute?queryName=number.of.tb.registrations'/>" + "&filterParams=" + dataString, function(data) {
                $("#allRegistrations").html(JSON.stringify(data));
            });
        });
    });
</script>
</@layout.defaultLayout>
