package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.service.FlashingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flashingLog")
public class FlashingLogCaptureController extends BaseController {

    private FlashingLogService flashingLogService;
    private DomainMapper domainMapper;

    @Autowired
    public FlashingLogCaptureController(FlashingLogService flashingLogService, DomainMapper domainMapper) {
        this.flashingLogService = flashingLogService;
        this.domainMapper = domainMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseBody
    public void callLogs(@RequestBody FlashingLogRequest flashingLogRequest) {
        flashingLogService.save(domainMapper.buildFlashingRequestLog(flashingLogRequest));
    }
}
