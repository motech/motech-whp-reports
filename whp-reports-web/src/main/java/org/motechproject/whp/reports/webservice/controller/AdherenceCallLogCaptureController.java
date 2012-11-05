package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.service.AdherenceCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/adherenceCallLog")
public class AdherenceCallLogCaptureController extends BaseController {

    AdherenceCallLogService callLogService;

    @Autowired
    public AdherenceCallLogCaptureController(AdherenceCallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseStatus(value = HttpStatus.OK)
    public void callLogs(@RequestBody AdherenceCallLogRequest callLogRequest) {
        callLogService.save(DomainBuilder.buildCallLog(callLogRequest));
    }
}
