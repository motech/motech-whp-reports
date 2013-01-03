package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.AdherenceSubmissionRequest;
import org.motechproject.whp.reports.service.ProviderReminderCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adherencesubmission")
public class AdherenceSubmissionController extends BaseController{

    ProviderReminderCallLogService providerReminderCallLogService;

    @Autowired
    public AdherenceSubmissionController(ProviderReminderCallLogService providerReminderCallLogService) {
        this.providerReminderCallLogService = providerReminderCallLogService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "request")
    @ResponseBody
    public void updateAdherenceCallLog(@RequestBody AdherenceSubmissionRequest adherenceSubmissionRequest){
        providerReminderCallLogService.updateAdherenceStatus(adherenceSubmissionRequest);
    }
}
