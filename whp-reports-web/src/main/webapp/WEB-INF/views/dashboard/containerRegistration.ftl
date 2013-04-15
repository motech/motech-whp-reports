<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="Container Registration Trends">
<h1>Container Registration Trends</h1>

<div>
    <#include "dashboardFilter.ftl"/>
        <ul class="row-fluid statistics">
            <li class="span2">
                All
                <span class="count" id = "allContainerRegistrations" data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations'/>"></span>
            </li>
            <li class="span8">
                <span class="nested-header">Container Registrations By Status</span>
                <div id = "containerRegistrationsByStatus" class="row-fluid"  data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations.by.status'/>">></div>
            </li>
        </ul>
    <div id = "containerRegistrationsByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations.by.district'/>"></div>
</div>

<#include "../layout/visualizationScripts.ftl"/>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/containerRegistrationDashboard.js'/>"></script>
<script id="containerStatuses" type="text/html">
    <ul class="unstyled">
        <li class='item'>
            <span class="count">{{ active }}</span>
            <span class="outcome">Active</span>
        </li>
        <li class='item'>
            <span class="count">{{ lab_results_pending }}</span>
            <span class="outcome">Lab Results Pending</span>
        </li>
        <li class='item'>
            <span class="count">{{ consultation_pending }}</span>
            <span class="outcome">Consultation Pending</span>
        </li>
        <li class='item'>
            <span class="count">{{ closed }}</span>
            <span class="outcome">Closed</span>
        </li>
    </ul>
</script>

</@layout.defaultLayout>
