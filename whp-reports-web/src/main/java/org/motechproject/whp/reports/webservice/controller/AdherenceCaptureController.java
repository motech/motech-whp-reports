package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.service.PatientAdherenceSubmissionService;
import org.motechproject.whp.reports.webservice.request.AdherenceCaptureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adherence")
public class AdherenceCaptureController extends BaseController {

    PatientAdherenceSubmissionService adherenceSubmissionService;

    @Autowired
    public AdherenceCaptureController(PatientAdherenceSubmissionService adherenceSubmissionService) {
        this.adherenceSubmissionService = adherenceSubmissionService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    public void callLogs(@RequestBody AdherenceCaptureRequest adherenceRequest) {
        adherenceSubmissionService.save(adherenceRequest.buildAdherenceSubmission());
    }
}
