<div class="row-fluid">
    <form name="dashboardFilter" id ="dashboardFilter" class="form-inline">
            <fieldset class="filters">
                    <label class="control-label">District</label>
                        <select id="district" name="district">
                            <option value="">All</option>
                        <#list districts as district>
                            <option value="${district.name}">${district.name}</option>
                        </#list>
                        </select>
                    <div class="input-append input-prepend">
                        <span class="add-on show-date-button"><i class="icon-calendar"></i></span>
                        <input class="dates" type="text" placeholder="From: dd/mm/yyyy"
                               data-date-format="dd/mm/yyyy" id="from_date" name="from_date"
                               data-date-range="to_date">
                        <span class="add-on clear-date-button"><i class="icon-remove-sign"></i></span>
                    </div>
                    <div class="input-append input-prepend">
                        <span class="add-on show-date-button"><i class="icon-calendar"></i></span>
                        <input class="dates" type="text" placeholder="To: dd/mm/yyyy"
                               data-date-format="dd/mm/yyyy"
                               id="to_date" name="to_date" data-date-range="from_date">
                        <span class="add-on clear-date-button"><i class="icon-remove-sign"></i></span>
                    </div>
                <button type="button" id="filter" class="btn btn-primary">Filter
                    <i class="icon-download icon-white"></i></button>
                <button id="clearFilter" type="reset" class="btn">Clear All</button>
            </fieldset>
    </form>
</div>