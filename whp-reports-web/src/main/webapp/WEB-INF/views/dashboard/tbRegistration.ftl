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

                <div id = "tbRegistrationsByOutcome" class="row-fluid">
                <ul class="unstyled" id="tbOutcomes">
                    <li>
                        <span class="count tb_registration_count"></span>
                        <span class="outcome"></span>
                    </li>
                </ul>
                </div>
            </li>
        </ul>


    <div>
        <input type="checkbox" id="tbRegistrationsByDistrictFlag" name="tbRegistrationsByDistrictFlag" value="false"/><label for="tbRegistrationsByDistrictFlag">Show Districts with zero count</label>
        <div id = "tbRegistrationsByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.tb.registrations.by.district'/>">
        </div>
    </div>
    <div>
        <input type="checkbox" id="providersByDistrictFlag" name="providersByDistrictFlag" value="false"/><label for="providersByDistrictFlag">Show Districts with zero count</label>
        <div id = "providersByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.providers.by.district'/>"></div>
    </div>
</div>

<#include "../layout/visualizationScripts.ftl"/>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/tbRegistrationDashboard.js'/>"></script>
</@layout.defaultLayout>
