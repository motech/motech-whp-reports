package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.patient.Patient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PatientController {


    @RequestMapping(value = "/patient/update")
    @ResponseBody
    public void update(@RequestBody Patient patient) {

    }

}
