<#import "/spring.ftl" as spring />
<#import "layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="WHP Statistics">
<h1>WHP Statistics</h1>

<div id="whpStatistics" data-url="<@spring.url '/bigquery/execute?queryName=whp.reports.stats'/>">
    <h4>Provider Statistics</h4>
    <table class="table table-bordered table-striped">
        <tbody id="providerStatsResults" class="whpStatsResults">
        <tr>
            <td><span>Number of Active Providers</span></td>
            <td><span class="active_providers"></span></td>
        </tr>
        <tr>
            <td><span>Number of Active TPC Providers</span></td>
            <td><span class="active_tpc_providers"></span></td>
        </tr>
        <tr>
            <td><span>Number of Active RHP Providers</span></td>
            <td><span class="active_rhp_providers"></span></td>
        </tr>
        </tbody>
    </table>

    <h4>Patient Statistics</h4>
    <table class="table table-bordered table-striped">
        <tbody id="patientStatsResults" class="whpStatsResults">
        <tr>
            <td><span>Number of Active Patients</span></td>
            <td><span class="active_patients"></span></td>
        </tr>
        <tr>
            <td><span>Number of Patients till date</span></td>
            <td><span class="all_patients"></span></td>
        </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/transparency/transparency.min.js'/>"></script>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/modules/whpStatistics.js'/>"></script>
</@layout.defaultLayout>