<#import "/spring.ftl" as spring />
<#setting date_format="MM/dd/yyyy"/>
<!DOCTYPE html>
<html>
<head>
    <title>Provider Performance Trends</title>
<#include "../layout/scripts.ftl"/>
</head>
<body class="printable-version">
<span id="providerPerformance"
      data-url="<@spring.url '/bigquery/execute?queryName=provider.performance.by.district'/>"></span>
<div class="container">
    <div id="navibar" class="navbar-fixed-top">
        <a href="<@spring.url '/' />">
            <img class="pull-right" src="<@spring.url '/resources-${applicationVersion}/images/whplogo.png'/>"/>
        </a>
    </div>
    <h3 class="text-center uppercase">Provider Performance Trends</h3>
    <hr class="bordered"/>
    <div class="row-fluid" id="mainContent">

        <div class="row-fluid">
            <table class="table table-bordered sharp fixed text-center">
                    <tr>
                        <th>District</th>
                        <th>No. of Providers with 0 adherence missing weeks</th>
                        <th>No. of Providers with 1-2 adherence missing weeks</th>
                        <th>No. of Providers with 3-5 adherence missing weeks</th>
                        <th>No. of Providers with 6-8 adherence missing weeks</th>
                    </tr>
                    <tbody id="providerPerformances" class="providerPerformances">
                    <!-- Generate an empty template row which can be applied on -->
                    <tr class="listing-entry">
                        <td><span class="district"></span></td>
                        <td><span class="zero_week_bucket"></span>&nbsp;(<span class="zeroProviderPercentage"></span>)%</td>
                        <td><span class="two_week_bucket"></span>&nbsp;(<span class="twoWeekProviderPercentage"></span>)%</td>
                        <td><span class="three_to_five_week_bucket"></span>&nbsp;(<span class="fiveWeekProviderPercentage"></span>)%</td>
                        <td><span class="six_to_eight_week_bucket"></span>&nbsp;(<span class="eightWeekProviderPercentage"></span>)%</td>
                    </tr>
                    </tbody>
             </table>
        </div>
    </div>
    <div>
        * Percentage out of total providers active in the past 8 weeks
    </div>
</div>
</body>
<script type="text/javascript"
        src="<@spring.url '/resources-${applicationVersion}/js/transparency/transparency.min.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/providerPerformanceTable.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/printProviderPerformanceTable.js'/>"></script>
</html>

