package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.service.AdherenceCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
