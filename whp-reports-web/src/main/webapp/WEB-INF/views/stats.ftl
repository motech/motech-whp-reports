<#import "/spring.ftl" as spring />
<link rel="stylesheet" type="text/css" href="<@spring.url '/resources-${applicationVersion}/styles/stats.css'/>"/>
<#import "layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="WHP Statistics">
<h1>WHP Statistics</h1>

<div id="whpStatistics" data-url="<@spring.url '/bigquery/execute?queryName=whp.reports.stats'/>">
    <div class="span6 offset3">
    <h4>Provider Statistics</h4>
    <table class="table table-bordered table-striped">
        <tbody id="providerStatsResults" class=" cellText whpStatsResults">
        <tr>
            <td><span>Number of Active Providers</span></td>
            <td class="fixedWidth"><span class="active_providers"></span></td>
        </tr>
        <tr>
            <td><span>Number of Active TPC Providers</span></td>
            <td class="fixedWidth"><span class="active_tpc_providers"></span></td>
        </tr>
        <tr>
            <td><span>Number of Active RHP Providers</span></td>
            <td class="fixedWidth"><span class="active_rhp_providers"></span></td>
        </tr>
        </tbody>
    </table>

    <h4>Patient Statistics</h4>
    <table class="table table-bordered table-striped">
        <tbody id="patientStatsResults" class=" cellText whpStatsResults">
        <tr>
            <td><span>Number of Active Patients</span></td>
            <td class="fixedWidth"><span class="active_patients"></span></td>
        </tr>
        <tr>
            <td><span>Number of Patients till date</span></td>
            <td class="fixedWidth"><span class="all_patients"></span></td>
        </tr>
        </tbody>
    </table>
    </div>
</div>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/transparency/transparency.min.js'/>"></script>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/modules/whpStatistics.js'/>"></script>
</@layout.defaultLayout>