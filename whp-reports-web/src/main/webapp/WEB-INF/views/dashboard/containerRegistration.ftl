<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="Container Registration Trends">
<h1>Container Registration Trends</h1>

<div>
    <#include "dashboardFilter.ftl"/>
        <ul class="row-fluid statistics">
            <li class="span2">
                Total Container Registrations
                <span class="count" id = "allContainerRegistrations" data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations'/>"></span>
            </li>
            <li class="span8">
                <span class="nested-header">Container Registrations By Status</span>
                <div id = "containerRegistrationsByStatus" class="row-fluid"  data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations.by.status'/>">
                    <ul class="unstyled" id="containerRegistrationsByStatusData">
                        <li class='item'>
                            <span class="count active"></span>
                            <span class="outcome">Active</span>
                        </li>
                        <li class='item'>
                            <span class="count lab_results_pending"></span>
                            <span class="outcome">Lab Results Pending</span>
                        </li>
                        <li class='item'>
                            <span class="count consultation_pending"></span>
                            <span class="outcome">Consultation Pending</span>
                        </li>
                        <li class='item'>
                            <span class="count closed"></span>
                            <span class="outcome">Closed</span>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    <div>

        <input type="checkbox" id="containerRegistrationsByDistrictFlag" name="containerRegistrationsByDistrictFlag" value="false"/><label for="containerRegistrationsByDistrictFlag">Show Districts with zero count</label>
        <div id = "containerRegistrationsByDistrict" data-url="<@spring.url '/bigquery/execute?queryName=number.of.container.registrations.by.district'/>"></div>
    </div>
</div>

<#include "../layout/visualizationScripts.ftl"/>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/dashboardFilter.js'/>"></script>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/containerRegistrationDashboard.js'/>"></script>
</@layout.defaultLayout>
