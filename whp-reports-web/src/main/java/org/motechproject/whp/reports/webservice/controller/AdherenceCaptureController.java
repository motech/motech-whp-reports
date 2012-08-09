package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.service.PatientAdherenceSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/adherence")
public class AdherenceCaptureController extends BaseController {

    PatientAdherenceSubmissionService adherenceSubmissionService;

    @Autowired
    public AdherenceCaptureController(PatientAdherenceSubmissionService adherenceSubmissionService) {
        this.adherenceSubmissionService = adherenceSubmissionService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseStatus(value = HttpStatus.OK)
    public void callLogs(@RequestBody AdherenceCaptureRequest adherenceRequest) {
        adherenceSubmissionService.save(DomainBuilder.buildAdherenceSubmission(adherenceRequest));
    }
}
