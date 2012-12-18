package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.ProviderReminderCallLogRequest;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.mapper.ProviderReminderCallLogMapper;
import org.motechproject.whp.reports.service.ProviderReminderCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/providerReminderCallLog")
public class ProviderReminderCallLogController extends BaseController {

    private final ProviderReminderCallLogService providerReminderCallLogService;
    private final ProviderReminderCallLogMapper providerReminderCallLogMapper;

    @Autowired
    public ProviderReminderCallLogController(ProviderReminderCallLogService providerReminderCallLogService, ProviderReminderCallLogMapper providerReminderCallLogMapper) {
        this.providerReminderCallLogService = providerReminderCallLogService;
        this.providerReminderCallLogMapper = providerReminderCallLogMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseBody
    public void callLogs(@RequestBody ProviderReminderCallLogRequest request) {
        ProviderReminderCallLog callLog = providerReminderCallLogMapper.map(request);
        providerReminderCallLogService.save(callLog);
    }
}
