<#import "/spring.ftl" as spring />
<#import "../layout/default-with-menu.ftl" as layout>
<@layout.defaultLayout title="TB Registration Trends">
<h1>Patient Alert Effectiveness</h1>

<div class="visualization" id="alertEffectiveness" data-url="<@spring.url '/bigquery/execute?queryName=patient.ivrAlerts.effectiveness'/>">

</div>
<#include "../layout/visualizationScripts.ftl"/>
<script type="text/javascript" src="<@spring.url '/resources-${applicationVersion}/js/modules/patientIvrAlerts.js'/>"></script>
</@layout.defaultLayout>