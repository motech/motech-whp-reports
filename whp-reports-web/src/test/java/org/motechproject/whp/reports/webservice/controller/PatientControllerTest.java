package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.builder.PatientRequestBuilder;
import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.springframework.http.MediaType;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class PatientControllerTest extends ControllerTest{

    PatientController patientController;

    @Before
    public void setUp() throws Exception {
        patientController = new PatientController();
    }

    @Test
    public void shouldConvertRequestBodyToPatient() throws Exception {
        PatientDTO patientDTO = new PatientRequestBuilder().withDefaults().build();
        String requestJSON = getJSON(patientDTO);

        standaloneSetup(patientController).build()
                .perform(post("/patient/update").body(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
}
