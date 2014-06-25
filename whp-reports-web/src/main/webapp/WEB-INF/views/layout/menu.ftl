<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<ul class="nav nav-pills pull-left">

<@security.authorize ifAnyGranted="IT_ADMIN, CMF_ADMIN">
    <li><a id="whpUrl" href="${whpURL}" target="_blank" >WHP Application</a></li>
</@security.authorize>
 <@security.authorize ifAnyGranted="IT_ADMIN, CMF_ADMIN, REPORT_USER">
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-download-alt icon-white"></i> Downloads <b class="caret"></b></a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="Menu" id="downloadLinks">
            <li>
                <a href="<@spring.message 'whp.reports.adherence.report'/>">All adherence data</a>
            </li>
            <li>
                <a href="<@spring.message 'whp.reports.adherence.audit.report'/>">All adherence audit data</a>
            </li>
            <li>
                <a href="/whp-reports/containerReports">Container tracking data</a>
            </li>
            <li>
                <a href="<@spring.message 'whp.reports.provider.reminder.call.report'/>">Provider reminder call logs</a>
            </li>
            <li>
                <a href="<@spring.message 'whp.reports.patient.alert.call.log.report'/>">Patient alert call logs</a>
            </li>
            <li>
                <a href="/whp-reports/patientReportsFilter">Patient Reports</a>
            </li>
        </ul>
    </li>
 <li class="dropdown">
     <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-white"></i> Summary <b class="caret"></b></a>
     <ul class="dropdown-menu" role="menu" aria-labelledby="Menu" id="summaryLinks">
        <li><a id="tbRegistrationSummary" href="/whp-reports/dashboard/tbRegistration" >TB Registrations</a></li>
        <li><a id="containerRegistrationVisualization" href="/whp-reports/dashboard/containerRegistration" >Container Registrations</a></li>
        <li><a id="providerPerformanceSummary" href="/whp-reports/dashboard/providerPerformance" >Provider Performance </a></li>
        <li><a href="/whp-reports/stats">Statistics</a></li>
     </ul>
 </li>

<li><a id="patientIvrAlertsVisualization" href="/whp-reports/visualization/patientIvrAlerts" >Patient IVR Alerts </a></li>

</@security.authorize>
<@security.authorize ifAnyGranted="IT_ADMIN, CMF_ADMIN">
    <li><a  href = "/whp-reports/crud/DoNotCallEntry/list" target="_blank">Do Not Call List</a></li>
</@security.authorize>
<@security.authorize ifAnyGranted="IT_ADMIN">
    <li><a  href = "/whp-reports/crud/District/list" target="_blank" >Manage Districts</a></li>
</@security.authorize>
</ul>

