package org.motechproject.whp.reports.export.query.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Therapy;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationReportingExportContext.xml")
public class ReportQueryDAOIT {

    @Autowired
    ReportQueryDAO reportQueryDAO;
    @Autowired
    PatientRepository patientRepository;

    Patient patient;

    @Before
    public void setUp() {
        patient = new PatientBuilder().withDefaults().withPatientId("patient").build();
        patientRepository.save(patient);
    }

    @Test
    public void shouldReturnPatientSummaries() {
        List<PatientSummary> patientList = reportQueryDAO.getPatientSummaries();

        assertEquals(1, patientList.size());

        PatientSummary summary = patientList.get(0);
        assertEquals(patient.getFirstName(), summary.getFirstName());
        assertEquals(patient.getLastName(), summary.getLastName());
        Therapy therapy = patient.getTherapies().get(0);
        assertEquals(therapy.getPatientAge(), summary.getAge());
        assertEquals(patient.getTherapies().get(0).getPatientAge(), summary.getAge());
        assertEquals(patient.getGender(), summary.getGender());
        assertEquals(patient.getPatientId(), summary.getPatientId());
        Treatment treatment = therapy.getTreatments().get(0);
        assertEquals(treatment.getTbId(), summary.getTbId());
        assertEquals(treatment.getProviderId() , summary.getProviderId());
        assertEquals(patient.getPatientAddress().getVillage(), summary.getVillage());
        assertEquals(treatment.getProviderDistrict(), summary.getProviderDistrict());
        assertEquals(therapy.getTreatmentCategory(), summary.getTreatmentCategory());
        assertEquals(treatment.getStartDate(), summary.getTbRegistrationDate());
        assertEquals(therapy.getStartDate(), summary.getTreatmentStartDate());
        assertEquals(therapy.getDiseaseClass(), summary.getDiseaseClass());
        assertEquals(treatment.getPatientType(), summary.getPatientType());
        assertEquals(therapy.getIpPillsTaken(), summary.getIpPillsTaken());
        assertEquals(therapy.getIpTotalDoses(), summary.getIpTotalDoses());
        assertEquals(therapy.getCpPillsTaken(), summary.getCpPillsTaken());
        assertEquals(therapy.getCpTotalDoses(), summary.getCpTotalDoses());
//        assertEquals(therapy.getIp, summary.getIpTreatmentProgress());
//        assertEquals(patient., summary.getCpTreatmentProgress());
//        assertEquals(patient., summary.getCumulativeMissedDoses());
        assertEquals(treatment.getTreatmentOutcome(), summary.getTreatmentOutcome());
        assertEquals(therapy.getCloseDate(), summary.getTreatmentClosingDate());
        assertEquals(treatment.getPreTreatmentSmearTestResult(), summary.getPretreatmentResult());
        assertEquals(treatment.getPreTreatmentWeight(), summary.getPretreatmentWeight());
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}
