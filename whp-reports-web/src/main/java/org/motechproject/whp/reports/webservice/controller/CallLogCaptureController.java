package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.service.CallLogService;
import org.motechproject.whp.reports.webservice.request.CallLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/calllog")
public class CallLogCaptureController extends BaseController {

    CallLogService callLogService;

    @Autowired
    public CallLogCaptureController(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    public void callLogs(@RequestBody CallLogRequest callLogRequest) {
        callLogService.save(callLogRequest.createCallLog());
    }
}
