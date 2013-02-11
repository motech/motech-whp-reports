package org.motechproject.whp.reports.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.builder.PatientRequestBuilder;
import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.contract.patient.TherapyDTO;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingDomainContext.xml")
public class PatientServiceIT {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Test
    @Transactional
    public void shouldCreateNewPatient() {
        PatientDTO patientDTO = new PatientRequestBuilder().withDefaults().build();
        patientService.update(patientDTO);


        Patient patientFromDatabase = patientService.getPatient(patientDTO.getPatientId());

        assertNotNull(patientFromDatabase.getId());
        assertNotNull(patientFromDatabase.getPatientAddress().getId());
        assertNotNull(patientFromDatabase.getPatientAlerts().getId());
        assertNotNull(patientFromDatabase.getTherapies());
        assertNotNull(patientFromDatabase.getTherapies().get(0).getId());
        assertNotNull(patientFromDatabase.getTherapies().get(0).getTreatments().get(0).getId());
    }

    @Test
    @Transactional
    public void shouldUpdateExistingPatient() {
        PatientDTO patientDTO = new PatientRequestBuilder().withDefaults().build();
        patientService.update(patientDTO);

        PatientDTO newPatientDTO = new PatientRequestBuilder().withDefaults().build();

        newPatientDTO.setFirstName("newName");
        TherapyDTO therapyDTO = newPatientDTO.getTherapies().get(0);
        therapyDTO.setDiseaseClass("newDisease");
        therapyDTO.getTreatments().get(0).setTreatmentOutcome("newOutcome");
        newPatientDTO.getPatientAddress().setBlock("newBlock");
        newPatientDTO.getPatientAlerts().setAdherenceMissingWeeks(100);

        patientService.update(newPatientDTO);

        Patient patientFromDatabase = patientService.getPatient(newPatientDTO.getPatientId());

        assertEquals("newName", patientFromDatabase.getFirstName());
        assertEquals("newDisease", patientFromDatabase.getTherapies().get(0).getDiseaseClass());
        assertEquals("newOutcome", patientFromDatabase.getTherapies().get(0).getTreatments().get(0).getTreatmentOutcome());
        assertEquals("newBlock", patientFromDatabase.getPatientAddress().getBlock());
        assertEquals(100, patientFromDatabase.getPatientAlerts().getAdherenceMissingWeeks());
    }

    @After
    public void tearDown() throws Exception {
        patientRepository.deleteAll();
    }
}
