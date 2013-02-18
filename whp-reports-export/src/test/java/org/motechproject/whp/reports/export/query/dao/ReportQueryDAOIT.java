package org.motechproject.whp.reports.export.query.dao;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Therapy;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
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

    PatientReportRequest patientReportRequest;

    Patient patient;

    @Before
    public void setUp() {
        patient = new PatientBuilder().withDefaults().withPatientId("patient").build();
        patientRepository.save(patient);
        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
    }

    @Test
    public void shouldReturnPatientSummaries() {
        List<PatientSummary> patientList = reportQueryDAO.getPatientSummaries(patientReportRequest);

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
        assertEquals(therapy.getCumulativeMissedDoses(), summary.getCumulativeMissedDoses());
        assertEquals(treatment.getTreatmentOutcome(), summary.getTreatmentOutcome());
        assertEquals(treatment.getEndDate(), summary.getTreatmentClosingDate());
        assertEquals(treatment.getPreTreatmentSmearTestResult(), summary.getPreTreatmentSputumResult());
        assertEquals(treatment.getPreTreatmentWeight(), summary.getPreTreatmentWeight());
        assertEquals("10/20 (50.00%)", summary.getIpTreatmentProgress());
        assertEquals("10/20 (50.00%)", summary.getCpTreatmentProgress());
    }

    @Test
    public void shouldReturnPatientForGivenDistrict() {
        String newDistrict = "newDistrict";
        Patient patientInNewDistrict = new PatientBuilder().withDefaults().withDistrict(newDistrict).withPatientId("patient2").build();
        patientRepository.save(patientInNewDistrict);

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict(newDistrict);
        List<PatientSummary> patientList = reportQueryDAO.getPatientSummaries(patientReportRequest);

        assertEquals(1, patientList.size());
        assertEquals(patientInNewDistrict.getPatientId(), patientList.get(0).getPatientId());
    }

    @Test
    public void shouldReturnPatientForGivenTbRegistrationDate() {
        Patient patientWithNewTbRegistrationDate = new PatientBuilder().withDefaults().withTbRegistrationDate(new LocalDate(2012, 12, 25)).withPatientId("patient2").build();
        patientRepository.save(patientWithNewTbRegistrationDate);

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setFrom("10/12/2012");
        patientReportRequest.setTo("31/12/2012");
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
        List<PatientSummary> patientList = reportQueryDAO.getPatientSummaries(patientReportRequest);

        assertEquals(1, patientList.size());
        assertEquals(patientWithNewTbRegistrationDate.getPatientId(), patientList.get(0).getPatientId());
    }

    @Test
    public void shouldReturnPatientForGivenDistrictAndTbRegistrationDate() {
        Patient expectedPatient = new PatientBuilder()
                .withDefaults()
                .withDistrict("newDistrict")
                .withTbRegistrationDate(new LocalDate(2012, 12, 25)).withPatientId("patient2").build();

        patientRepository.save(expectedPatient);

        PatientReportRequest patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict("newDistrict");
        patientReportRequest.setFrom("10/12/2012");
        patientReportRequest.setTo("31/12/2012");
        List<PatientSummary> patientList = reportQueryDAO.getPatientSummaries(patientReportRequest);

        assertEquals(1, patientList.size());
        assertEquals(expectedPatient.getPatientId(), patientList.get(0).getPatientId());
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}
