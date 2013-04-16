<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="TB Registration Trends">
<h1>TB Registration Trends</h1>

<div>
    <#include "dashboardFilter.ftl"/>
        <ul class="row-fluid statistics">
            <li class="span2">
                Total TB Registrations
                <span class="count" id = "allRegistrations" data-url="<@spring.url '/bigquery/execute?queryName=number.of.tb.registrations'/>"></span>
            </li>
            <li class="span2">
                Closed TB Registrations
                <span class="count" id = "closedRegistrations" data-url="<@spring.url '/bigquery/execute?queryName=number.of.tb.registrations.by.outcome'/>"></span>
            </li>
            <li class="span8">
                <span class="nested-header">TB Registrations Grouped By Outcome</span>
                <div id = "tbRegistrationsByOutcome" class="row-fluid"></div>
            </li>
        </ul>
    <div>
        <label for="tbRegistrationsByDistrictFlag">Show Districts with zero count</label>
        <input type="checkbox" id="tbRegistrationsByDistrictFlag" name="tbRegistrationsByDistrictFlag" value="false"/>
        <div id = "tbRegistrationsByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.tb.registrations.by.district'/>">
        </div>
    </div>
    <div>
        <label for="providersByDistrictFlag">Show Districts with zero count</label>
        <input type="checkbox" id="providersByDistrictFlag" name="providersByDistrictFlag" value="false"/>
        <div id = "providersByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.providers.by.district'/>"></div>
    </div>
</div>

<#include "../layout/visualizationScripts.ftl"/>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/tbRegistrationDashboard.js'/>"></script>
<script id="tbOutcomes" type="text/html">
    <ul class="unstyled">
        {{#rows}}
            <li class='item'>
                <span class="count">{{ tb_registration_count }}</span>
                <span class="outcome">{{ outcome }} </span>
            </li>
        {{/rows}}
    </ul>
</script>
</@layout.defaultLayout>
