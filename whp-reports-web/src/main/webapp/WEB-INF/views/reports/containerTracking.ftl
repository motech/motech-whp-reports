<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="Container Tracking Report">
<h1>Container Tracking Report</h1>


<div id = "containerTracking" data-url="<@spring.url '/download/containerReport.xls' />">
    <#include "../dashboard/dashboardFilter.ftl"/>
</div>

<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/containerTracking.js'/>"></script>
<script type="text/javascript">
    document.getElementById("filter").textContent = 'Download';
    document.getElementById("filter").className += ' downloadLinks';
</script>

</@layout.defaultLayout>