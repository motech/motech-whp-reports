package org.motechproject.whp.reports.webservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.service.AdherenceRecordService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdherenceRecordControllerTest extends ControllerTest {

    AdherenceRecordController adherenceRecordController;

    @Mock
    AdherenceRecordService adherenceRecordService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        adherenceRecordController = new AdherenceRecordController(adherenceRecordService);
    }

    @Test
    public void shouldSaveAdherenceRecord() throws Exception {
        AdherenceRecordDTO adherenceRecordDTO = createAdherenceRecordDTO();

        String requestJSON = getJSON(adherenceRecordDTO);

        MockMvcBuilders.standaloneSetup(adherenceRecordController).build()
                .perform(post("/adherence/record").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adherenceRecordService).save(adherenceRecordDTO);
    }

    @Test
    public void shouldDeleteAdherenceRecord() throws Exception {
        AdherenceRecordDTO adherenceRecordDTO = createAdherenceRecordDTO();
        List<AdherenceRecordDTO> adherenceRecordDTOs = Arrays.asList(adherenceRecordDTO);

        String requestJSON = getJSON(adherenceRecordDTOs);

        MockMvcBuilders.standaloneSetup(adherenceRecordController).build()
                .perform(post("/adherence/delete").content(requestJSON.getBytes()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(adherenceRecordService).delete(adherenceRecordDTOs);
    }


    private AdherenceRecordDTO createAdherenceRecordDTO() {
        AdherenceRecordDTO adherenceRecordDTO = new AdherenceRecordDTO();
        adherenceRecordDTO.setDistrict("district");
        adherenceRecordDTO.setPatientId("patientId");
        adherenceRecordDTO.setPillDate(new Date(0));
        adherenceRecordDTO.setPillStatus("Taken");
        adherenceRecordDTO.setProviderId("providerId");
        adherenceRecordDTO.setTbId("tbId");
        adherenceRecordDTO.setTherapyId("therapyId");
        return adherenceRecordDTO;
    }
}
