package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.service.PatientAdherenceSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adherence")
public class AdherenceCaptureController extends BaseController {

    PatientAdherenceSubmissionService adherenceSubmissionService;
    private final DomainMapper domainMapper;

    @Autowired
    public AdherenceCaptureController(PatientAdherenceSubmissionService adherenceSubmissionService, DomainMapper domainMapper) {
        this.adherenceSubmissionService = adherenceSubmissionService;
        this.domainMapper = domainMapper;
    }

    @RequestMapping(method = RequestMethod.POST, value = "measure")
    @ResponseBody
    public void callLogs(@RequestBody AdherenceCaptureRequest adherenceRequest) {
        adherenceSubmissionService.save(domainMapper.mapAdherenceSubmission(adherenceRequest));
    }

    @RequestMapping(method = RequestMethod.POST, value = "measureDelete")
    @ResponseBody
    public void deleteCallLogs(@RequestBody String patientId) {
        adherenceSubmissionService.delete(patientId);
    }
}
