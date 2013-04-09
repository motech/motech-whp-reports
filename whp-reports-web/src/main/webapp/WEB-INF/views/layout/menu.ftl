<ul class="nav nav-pills pull-left">
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
                <a href="/whp-reports/reportsFilter?reportType=<@spring.message 'whp.reports.type.container'/>">Container tracking data</a>
            </li>
            <li>
                <a href="<@spring.message 'whp.reports.provider.reminder.call.report'/>">Provider reminder call logs</a>
            </li>
            <li>
                <a href="<@spring.message 'whp.reports.patient.alert.call.log.report'/>">Patient alert call logs</a>
            </li>
            <li>
                <a href="/whp-reports/reportsFilter?reportType=<@spring.message 'whp.reports.type.patient'/>">Patient Reports</a>
            </li>
        </ul>
    </li>
</ul>

