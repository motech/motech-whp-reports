package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.service.FlashingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/flashingLog")
public class FlashingLogCaptureController extends BaseController {

    FlashingLogService flashingLogService;

    @Autowired
    public FlashingLogCaptureController(FlashingLogService flashingLogService) {
        this.flashingLogService = flashingLogService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseStatus(value = HttpStatus.OK)
    public void callLogs(@RequestBody FlashingLogRequest flashingLogRequest) {
        flashingLogService.save(DomainBuilder.buildFlashingRequestLog(flashingLogRequest));
    }
}
