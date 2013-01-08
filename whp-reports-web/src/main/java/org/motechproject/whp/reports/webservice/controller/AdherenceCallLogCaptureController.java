package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.AdherenceCallStatusRequest;
import org.motechproject.whp.reports.service.AdherenceCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/adherenceCallLog")
public class AdherenceCallLogCaptureController extends BaseController {

    AdherenceCallLogService callLogService;
    DomainMapper domainMapper;

    @Autowired
    public AdherenceCallLogCaptureController(AdherenceCallLogService callLogService, DomainMapper domainMapper) {
        this.callLogService = callLogService;
        this.domainMapper = domainMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseBody
    public void callLogs(@RequestBody AdherenceCallLogRequest callLogRequest) {
        callLogService.save(domainMapper.mapAdherenceCallLog(callLogRequest));
    }

    @RequestMapping(method = RequestMethod.POST, value = "status/measure")
    @ResponseBody
    public void callLogs(@RequestBody @Valid AdherenceCallStatusRequest callStatusRequest) {
        callLogs(callStatusRequest.toCallLogRequest());
    }
}
