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
            <th>No. of Providers with 0 adherence missing weeks</th>
            <th>No. of Providers with 1-2 adherence missing weeks</th>
            <th>No. of Providers with 3-5 adherence missing weeks</th>
            <th>No. of Providers with 6-8 adherence missing weeks</th>
        </tr>
            <tbody id="providerPerformances" class="providerPerformances">
            <!-- Generate an empty template row which can be applied on -->
            <tr class="listing-entry" id="providerPerformances" >
                <td><span class="district"></span></td>
                <td><span class="zero_week_bucket"></span>&nbsp;(<span class="zeroProviderPercentage"></span>)%</td>
                <td><span class="two_week_bucket"></span>&nbsp;(<span class="twoWeekProviderPercentage"></span>)%</td>
                <td><span class="three_to_five_week_bucket"></span>&nbsp;(<span class="fiveWeekProviderPercentage"></span>)%</td>
                <td><span class="six_to_eight_week_bucket"></span>&nbsp;(<span class="eightWeekProviderPercentage"></span>)%</td>
            </tr>

            </tbody>

        </table>
        </div>
    <div>
        * Provider performance count for past 8 treatment weeks
        <br>
        ** Percentage out of total providers active in the past 8 treatment weeks
    </div>
</div>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/transparency/transparency.min.js'/>"></script>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/modules/providerPerformanceTable.js'/>"></script>
</@layout.defaultLayout>
