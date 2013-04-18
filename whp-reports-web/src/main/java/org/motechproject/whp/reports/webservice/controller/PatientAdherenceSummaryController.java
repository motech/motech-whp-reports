package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.query.PatientAdherenceSummary;
import org.motechproject.whp.reports.service.PatientAdherenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PatientAdherenceSummaryController  extends BaseController{
    private PatientAdherenceDataService patientAdherenceDataService;

    @Autowired
    public PatientAdherenceSummaryController(PatientAdherenceDataService patientAdherenceDataService) {
        this.patientAdherenceDataService = patientAdherenceDataService;
    }

    @RequestMapping(method = GET, value = "/patientsWithoutAdherence", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PatientAdherenceSummary> patientAdherenceSummaries(@RequestParam(defaultValue = "0") int skip, @RequestParam int limit){
        return patientAdherenceDataService.getPatientsWithMissingAdherence(skip, limit);
    }
}
