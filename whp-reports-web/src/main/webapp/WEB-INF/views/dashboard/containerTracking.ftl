<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="Container Tracking Report">
<h1>Container Tracking Report</h1>

<div>
    <#include "dashboardFilter.ftl"/>
</div>

<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/containerTracking.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
</@layout.defaultLayout>