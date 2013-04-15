<ul class="nav nav-pills pull-left">

    <li><a id="whpUrl" href="${whpURL}" target="_blank" >WHP Application</a></li>

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

    <li><a id="tbRegistrationSummary" href="/whp-reports/dashboard/tbRegistration" >TB Registration Summary</a></li>
    <li><a id="containerRegistrationVisualization" href="/whp-reports/dashboard/containerRegistration" >Container Registration</a></li>
</ul>

