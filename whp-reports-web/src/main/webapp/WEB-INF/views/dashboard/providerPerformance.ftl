<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="Provider Performance Trends">
<h1>Provider Performance Trends</h1>

<div>
    <a id="printTreatmentCard" target="_blank" class="btn btn-large btn-info pull-right"
       href="<@spring.url '/dashboard/printProviderPerformance'/>"><i class="icon-print icon-white"></i>
        Print</a>

    <ul class="row-fluid statistics">
        <span id="providerPerformance"
              data-url="<@spring.url '/bigquery/execute?queryName=provider.performance.by.district'/>"></span>
    </ul>
    <div>
    <table class="table table-bordered table-striped">
        <tr>
            <th>District</th>
            <th>zero_week_bucket</th>
            <th>two_week_bucket</th>
            <th>three_to_five_week_bucket</th>
            <th>six_to_eight_week_bucket</th>
        </tr>
            <tbody id="providerPerformances" class="providerPerformances">
            <!-- Generate an empty template row which can be applied on -->
            <tr class="listing-entry" id="providerPerformances" >
                <td><span class="district"></span></td>
                <td><span class="zero_week_bucket"></span></td>
                <td><span class="two_week_bucket"></span></td>
                <td><span class="three_to_five_week_bucket"></span></td>
                <td><span class="six_to_eight_week_bucket"></span></td>
            </tr>

            </tbody>

        </table>
        </div>
</div>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/transparency/transparency.min.js'/>"></script>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/modules/providerPerformanceTable.js'/>"></script>
</@layout.defaultLayout>
