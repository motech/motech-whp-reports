package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController extends BaseController{

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patient/update")
    @ResponseBody
    public void update(@RequestBody PatientDTO patientDTO) {
        patientService.update(patientDTO);
    }

}
