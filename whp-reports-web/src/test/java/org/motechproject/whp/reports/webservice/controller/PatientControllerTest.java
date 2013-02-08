package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.whp.reports.builder.PatientRequestBuilder;
import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.service.PatientService;
import org.springframework.http.MediaType;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class PatientControllerTest extends ControllerTest{

    PatientController patientController;

    @Mock
    PatientService patientService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        patientController = new PatientController(patientService);
    }

    @Test
    public void shouldUpdatePatient() throws Exception {
        PatientDTO patientDTO = new PatientRequestBuilder().withDefaults().build();
        String requestJSON = getJSON(patientDTO);

        standaloneSetup(patientController).build()
                .perform(post("/patient/update").body(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<PatientDTO> patientDTOArgumentCaptor = ArgumentCaptor.forClass(PatientDTO.class);
        verify(patientService).update(patientDTOArgumentCaptor.capture());

        assertEquals(patientDTO.getPatientId(), patientDTOArgumentCaptor.getValue().getPatientId());
    }
    
}
