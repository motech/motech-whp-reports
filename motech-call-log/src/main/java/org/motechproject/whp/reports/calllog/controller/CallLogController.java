package org.motechproject.whp.reports.calllog.controller;

import org.apache.log4j.Logger;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.service.CallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/callLog")
public class CallLogController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private CallLogService callLogService;

    @Autowired
    public CallLogController(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void log(@RequestBody CallLogRequest callLogRequest) {
        callLogService.add(callLogRequest);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(RuntimeException ex) {
        logger.error("Error occurred", ex);
        throw ex;
    }
}
