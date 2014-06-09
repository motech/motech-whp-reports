package org.motechproject.whp.reports.webservice.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.motechproject.whp.reports.contract.PatientReminderCallLogRequest;
import org.motechproject.whp.reports.domain.measure.calllog.PatientReminderCallLog;
import org.motechproject.whp.reports.mapper.PatientReminderCallLogMapper;
import org.motechproject.whp.reports.service.PatientReminderCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/patientReminderCallLog",
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
public class PatientReminderCallLogController {

	private final PatientReminderCallLogService patientReminderCallLogService;
    private final PatientReminderCallLogMapper patientReminderCallLogMapper;

    @Autowired
    public PatientReminderCallLogController(PatientReminderCallLogService providerReminderCallLogService, PatientReminderCallLogMapper providerReminderCallLogMapper) {
        this.patientReminderCallLogService = providerReminderCallLogService;
        this.patientReminderCallLogMapper = providerReminderCallLogMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseBody
    public void callLogs(@RequestBody @Valid PatientReminderCallLogRequest request) {
        PatientReminderCallLog callLog = patientReminderCallLogMapper.map(request);
        patientReminderCallLogService.save(callLog);
    }
}
