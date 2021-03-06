package org.motechproject.whp.reports.export.query.dao;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.builder.AdherenceAuditLogBuilder;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.contract.enums.YesNo;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.domain.measure.calllog.ProviderReminderCallLog;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Therapy;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.domain.patient.TreatmentDetails;
import org.motechproject.whp.reports.export.query.builder.WhpExcelReportBuilder;
import org.motechproject.whp.reports.export.query.model.*;
import org.motechproject.whp.reports.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlDate;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlTimestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationReportingExportContext.xml")
public class ReportQueryDAOIT {

    @Autowired
    ReportQueryDAO reportQueryDAO;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AdherenceAuditLogRepository adherenceAuditLogRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    @Qualifier(value = "adherenceRecordRepository")
    AdherenceRecordRepository adherenceRecordRepository;
    @Autowired
    ProviderReminderCallLogRepository providerReminderCallLogRepository;
    @Autowired
    WhpExcelReportBuilder whpExcelReportBuilder;

    PatientReportRequest patientReportRequest;
    ProviderReportRequest providerReportRequest;

    Patient patient;
    Patient testPatient;

    Provider provider;
    Provider testProvider;

    public final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    DateTime now;

    @Before
    public void setUp() {
        patient = new PatientBuilder().withDefaults().withPatientId("patient").build();
        testPatient = new PatientBuilder().withDefaults().withPatientId("testpatient").withTbRegistrationDate(new LocalDate(2012, 12, 25)).withDistrict("TestDistrict").build();
        patientRepository.save(asList(patient, testPatient));
        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setReportType(PatientReportType.SUMMARY_REPORT);
        
        providerReportRequest = new ProviderReportRequest();

        now = DateTime.now();
    }

    @Test
    public void shouldReturnPatientSummariesExcludingTestData() {
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
        assertEquals(treatment.getProviderId(), summary.getProviderId());
        assertEquals(patient.getPatientAddress().getVillage(), summary.getVillage());
        assertEquals(treatment.getProviderDistrict(), summary.getProviderDistrict());
        assertEquals(therapy.getTreatmentCategory(), summary.getTreatmentCategory());
        assertEquals(treatment.getStartDate(), summary.getTbRegistrationDate());
        assertEquals(therapy.getStartDate(), summary.getTreatmentStartDate());
        assertEquals(therapy.getDiseaseClass(), summary.getDiseaseClass());
        assertEquals(treatment.getPatientType(), summary.getPatientType());
        assertEquals(therapy.getIpPillsTaken(), summary.getIpPillsTaken());
        assertEquals(therapy.getIpTotalDoses(), summary.getIpTotalDoses());
        assertEquals(therapy.getEipPillsTaken(), summary.getEipPillsTaken());
        assertEquals(therapy.getEipTotalDoses(), summary.getEipTotalDoses());
        assertEquals(therapy.getCpPillsTaken(), summary.getCpPillsTaken());
        assertEquals(therapy.getCpTotalDoses(), summary.getCpTotalDoses());
        assertEquals(therapy.getCumulativeMissedDoses(), summary.getCumulativeMissedDoses());
        assertEquals(treatment.getTreatmentOutcome(), summary.getTreatmentOutcome());
        assertEquals(treatment.getEndDate(), summary.getTreatmentClosingDate());
        assertEquals(treatment.getPreTreatmentSmearTestResult(), summary.getPreTreatmentSputumResult());
        assertEquals(treatment.getPreTreatmentWeight(), summary.getPreTreatmentWeight());
        assertEquals("20/40 (50.00%)", summary.getIpTreatmentProgress());
        assertEquals("10/20 (50.00%)", summary.getCpTreatmentProgress());
        assertEquals(treatment.getCreationDate(), summary.getCreationDate());

        Treatment currentTreatment = patient.getTherapies().get(0).getTreatments().get(0);
        TreatmentDetails expectedTreatmentDetails = currentTreatment.getTreatmentDetails();
        assertEquals(currentTreatment.getCloseTreatmentRemarks(), summary.getCloseTreatmentRemarks());
        assertTreatmentDetails(summary, expectedTreatmentDetails);

    }

    private void assertTreatmentDetails(PatientSummary summary, TreatmentDetails expectedTreatmentDetails) {
        assertEquals(expectedTreatmentDetails.getDistrictWithCode(), summary.getDistrictWithCode());
        assertEquals(expectedTreatmentDetails.getTbUnitWithCode(), summary.getTbUnitWithCode());
        assertEquals(expectedTreatmentDetails.getEpSite(), summary.getEpSite());
        assertEquals(expectedTreatmentDetails.getOtherInvestigations(), summary.getOtherInvestigations());
        assertEquals(expectedTreatmentDetails.getPreviousTreatmentHistory(), summary.getPreviousTreatmentHistory());
        assertEquals(expectedTreatmentDetails.getHivStatus(), summary.getHivStatus());
        assertEquals(expectedTreatmentDetails.getMembersBelowSixYears(), summary.getMembersBelowSixYears());
        assertEquals(expectedTreatmentDetails.getCmfDoctor(), summary.getCmfDoctor());
        assertEquals(expectedTreatmentDetails.getProviderType(), summary.getProviderType());
        assertEquals(expectedTreatmentDetails.getXpertDeviceNumber(), summary.getXpertDeviceNumber());
        assertEquals(expectedTreatmentDetails.getXpertTestDate(), summary.getXpertTestDate());
        assertEquals(expectedTreatmentDetails.getXpertTestResult(), summary.getXpertTestResult());
        assertEquals(expectedTreatmentDetails.getRifResistanceResult(), summary.getRifResistanceResult());
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
        patientRepository.save(asList(patientWithNewTbRegistrationDate));

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

    @Test
    public void shouldReturnAdherenceAuditLogsForThreeMonths() {
        DateTime today = DateTime.now();
        createProvider();
        createTestProvider();

        AdherenceAuditLog adherenceGivenByProvider = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenByProvider.setIsGivenByProvider("Y");

        AdherenceAuditLog adherenceGivenByTestProvider = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenByTestProvider.setProviderId(testProvider.getProviderId());

        AdherenceAuditLog adherenceLogFourMonthsAgo = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceLogFourMonthsAgo.setCreationTime(toSqlTimestamp(today.minusMonths(4)));
        adherenceLogFourMonthsAgo.setDoseDate(toSqlDate(today.minusMonths(1)));

        adherenceAuditLogRepository.save(asList(adherenceGivenByProvider, adherenceLogFourMonthsAgo, adherenceGivenByTestProvider));

        List<AdherenceAuditLogSummary> adherenceAuditLogSummaries = reportQueryDAO.getAdherenceAuditLogSummaries();
        assertThat(adherenceAuditLogSummaries.size(), is(1));

        assertThat(adherenceAuditLogSummaries.get(0).getCreationTime(), is(new Date(adherenceGivenByProvider.getCreationTime().getTime())));
        assertThat(adherenceAuditLogSummaries.get(0).getDistrict(), is(provider.getDistrict()));

        assertThat(formatter.format(adherenceAuditLogSummaries.get(0).getDoseDate()), is(formatter.format(adherenceGivenByProvider.getDoseDate())));
        assertThat(adherenceAuditLogSummaries.get(0).getNumberOfDosesTaken(), is(adherenceGivenByProvider.getNumberOfDosesTaken()));
        assertThat(adherenceAuditLogSummaries.get(0).getPatientId(), is(adherenceGivenByProvider.getPatientId()));
        assertThat(adherenceAuditLogSummaries.get(0).getPillStatus(), is(adherenceGivenByProvider.getPillStatus()));
        assertThat(adherenceAuditLogSummaries.get(0).getProviderId(), is(adherenceGivenByProvider.getProviderId()));
        assertThat(adherenceAuditLogSummaries.get(0).getSourceOfChange(), is(adherenceGivenByProvider.getChannel()));
        assertThat(adherenceAuditLogSummaries.get(0).getTbId(), is(adherenceGivenByProvider.getTbId()));
        assertThat(adherenceAuditLogSummaries.get(0).getUserId(), is(adherenceGivenByProvider.getUserId()));
        assertThat(adherenceAuditLogSummaries.get(0).getIsGivenByProvider(), is("Yes"));

    }

    @Test
    public void shouldOrderRecordsByCreationTime() {

        createProvider();

        DateTime today = DateTime.now();
        AdherenceAuditLog adherenceGivenByProvider = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenByProvider.setCreationTime(WHPDateTime.toSqlTimestamp(today));
        adherenceGivenByProvider.setPatientId("patient1");
        adherenceGivenByProvider.setIsGivenByProvider("Y");

        AdherenceAuditLog adherenceGivenByAdmin = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenByAdmin.setCreationTime(WHPDateTime.toSqlTimestamp(today.minusMinutes(35)));
        adherenceGivenByAdmin.setPatientId("patient2");
        adherenceGivenByProvider.setIsGivenByProvider("N");

        AdherenceAuditLog adherenceGivenBy4MonthsAgo = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenBy4MonthsAgo.setCreationTime(WHPDateTime.toSqlTimestamp(today.minusMonths(4)));
        adherenceGivenBy4MonthsAgo.setPatientId("patient3");

        AdherenceAuditLog adherenceGivenSameTimeAsPatient1 = new AdherenceAuditLogBuilder().withDefaults().build();
        adherenceGivenSameTimeAsPatient1.setCreationTime(WHPDateTime.toSqlTimestamp(today.minusMillis(2)));
        adherenceGivenSameTimeAsPatient1.setPatientId("patient4");

        adherenceAuditLogRepository.save(asList(adherenceGivenByProvider, adherenceGivenByAdmin, adherenceGivenBy4MonthsAgo, adherenceGivenSameTimeAsPatient1));

        List<AdherenceAuditLogSummary> adherenceAuditLogSummaries = reportQueryDAO.getAdherenceAuditLogSummaries();
        assertThat(adherenceAuditLogSummaries.size(), is(3));
        assertThat(adherenceAuditLogSummaries.get(0).getPatientId(), is("patient1"));
        assertThat(adherenceAuditLogSummaries.get(1).getPatientId(), is("patient4"));
        assertThat(adherenceAuditLogSummaries.get(2).getPatientId(), is("patient2"));


    }


    @Test
    public void shouldReturnAdherenceRecords() {

        createProvider();
        createTestProvider();

        java.sql.Date pillDate = toSqlDate(DateUtil.now());
        AdherenceRecord adherenceRecord = createAdherenceRecord("patientId", provider.getDistrict(), pillDate);
        AdherenceRecord testAdherenceRecord = createAdherenceRecord("patientId1", testProvider.getDistrict(), pillDate);
        adherenceRecordRepository.save(asList(adherenceRecord, testAdherenceRecord));

        List<AdherenceRecordSummary> adherenceRecordSummaries = reportQueryDAO.getAdherenceRecordSummaries();
        assertThat(adherenceRecordSummaries.size(), is(1));
        assertThat(adherenceRecordSummaries.get(0).getPatientId(), is("patientId"));
        assertThat(adherenceRecordSummaries.get(0).getTbId(), is("tbId"));
        assertThat(adherenceRecordSummaries.get(0).getDistrict(), is(provider.getDistrict()));
        assertThat(formatter.format(adherenceRecordSummaries.get(0).getAdherenceDate()), is(formatter.format(pillDate.getTime())));
        assertThat(adherenceRecordSummaries.get(0).getAdherenceValue(), is("Taken"));

    }

    @Test
    public void shouldOrderAdherenceRecordsByPillDateAndLimitToThreeMonths() {

        AdherenceRecord adherenceRecord1 = createAdherenceRecord("patientId1", "district", toSqlDate(now));
        AdherenceRecord adherenceRecord2 = createAdherenceRecord("patientId2", "district", toSqlDate(now.plusDays(2)));
        AdherenceRecord adherenceRecord3 = createAdherenceRecord("patientId3", "district", toSqlDate(now.plusDays(3)));
        AdherenceRecord adherenceRecord5 = createAdherenceRecord("patientId5", "district", toSqlDate(now.minusMonths(4)));

        adherenceRecordRepository.save(asList(adherenceRecord1, adherenceRecord2, adherenceRecord3, adherenceRecord5));

        List<AdherenceRecordSummary> adherenceRecordSummaries = reportQueryDAO.getAdherenceRecordSummaries();
        assertThat(adherenceRecordSummaries.size(), is(3));
        assertThat(adherenceRecordSummaries.get(0).getPatientId(), is("patientId3"));
        assertThat(adherenceRecordSummaries.get(1).getPatientId(), is("patientId2"));
        assertThat(adherenceRecordSummaries.get(2).getPatientId(), is("patientId1"));
    }

	@Test
	public void shouldReturnProviderForGivenDistrict() {

		createProvider();
		ProviderReminderCallLog providerReminderCallLog = createProviderReminderCallLog();
		providerReminderCallLogRepository.save(providerReminderCallLog);

		ProviderReportRequest providerReportRequest = new ProviderReportRequest();
		providerReportRequest.setDistrict("district");
		providerReportRequest
				.setReportType(ProviderReportType.REMINDER_CALL_LOG);
		List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = reportQueryDAO
				.getProviderReminderCallLogSummaries(providerReportRequest);

		 assertThat(providerReminderCallLogSummaries.size(), is(1));
	        ProviderReminderCallLogSummary callLog = providerReminderCallLogSummaries.get(0);
        assertThat(callLog.getCallId(), is(providerReminderCallLog.getCallId()));
        assertThat(callLog.getAdherenceReportedDisplayText(), is(YesNo.valueFromCode(providerReminderCallLog.getAdherenceReported()).getText()));
        assertThat(callLog.getAttempt(), is(providerReminderCallLog.getAttempt()));
        assertThat(callLog.getAttemptDateTime(), is(providerReminderCallLog.getAttemptTime()));
        assertThat(callLog.getStartDateTime(), is(providerReminderCallLog.getStartTime()));
        assertThat(callLog.getEndDateTime(), is(providerReminderCallLog.getEndTime()));
        assertThat(callLog.getDuration(), is(3));
        assertThat(callLog.getCallAnswered(), is(providerReminderCallLog.getCallAnswered()));
        assertThat(callLog.getReminderType(), is(providerReminderCallLog.getReminderType()));
        assertThat(callLog.getReminderDay(), notNullValue());
        assertThat(callLog.getProviderId(), is(providerReminderCallLog.getProviderId()));
        assertThat(callLog.getDisconnectionType(), is(providerReminderCallLog.getDisconnectionType()));
        assertThat(callLog.getDistrict(), is("district"));
    }

    @Test
    public void shouldReturnProviderReminderCallLogRecords() {
        createProvider();
        createTestProvider();

        ProviderReminderCallLog providerReminderCallLog = createProviderReminderCallLog();
        ProviderReminderCallLog testProviderReminderCallLog = createProviderReminderCallLog();
        testProviderReminderCallLog.setProviderId(testProvider.getProviderId());
        testProviderReminderCallLog.setCallId("callId1");

        providerReminderCallLogRepository.save(asList(providerReminderCallLog, testProviderReminderCallLog));

		providerReportRequest
				.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = reportQueryDAO.getProviderReminderCallLogSummaries(providerReportRequest);

        assertThat(providerReminderCallLogSummaries.size(), is(1));
        ProviderReminderCallLogSummary callLog = providerReminderCallLogSummaries.get(0);
        assertThat(callLog.getCallId(), is(providerReminderCallLog.getCallId()));
        assertThat(callLog.getAdherenceReportedDisplayText(), is(YesNo.valueFromCode(providerReminderCallLog.getAdherenceReported()).getText()));
        assertThat(callLog.getAttempt(), is(providerReminderCallLog.getAttempt()));
        assertThat(callLog.getAttemptDateTime(), is(providerReminderCallLog.getAttemptTime()));
        assertThat(callLog.getStartDateTime(), is(providerReminderCallLog.getStartTime()));
        assertThat(callLog.getEndDateTime(), is(providerReminderCallLog.getEndTime()));
        assertThat(callLog.getDuration(), is(3));
        assertThat(callLog.getCallAnswered(), is(providerReminderCallLog.getCallAnswered()));
        assertThat(callLog.getReminderType(), is(providerReminderCallLog.getReminderType()));
        assertThat(callLog.getReminderDay(), notNullValue());
        assertThat(callLog.getProviderId(), is(providerReminderCallLog.getProviderId()));
        assertThat(callLog.getDisconnectionType(), is(providerReminderCallLog.getDisconnectionType()));
        assertThat(callLog.getDistrict(), is("district"));
    }

    private ProviderReminderCallLog createProviderReminderCallLog() {

        ProviderReminderCallLog providerReminderCallLog = new ProviderReminderCallLog();
        providerReminderCallLog.setAdherenceReported("Y");
        providerReminderCallLog.setAttempt(1);

        providerReminderCallLog.setAttemptTime(new Timestamp(now.getMillis()));
        providerReminderCallLog.setStartTime(new Timestamp(now.getMillis()));
        providerReminderCallLog.setEndTime(new Timestamp(now.getMillis() + 3000L));
        providerReminderCallLog.setCallAnswered("Yes");
        providerReminderCallLog.setCallId("callId");
        providerReminderCallLog.setDisconnectionType("type");
        providerReminderCallLog.setMobileNumber("mobileNumber");
        providerReminderCallLog.setProviderId("providerId");
        providerReminderCallLog.setReminderType("reminderType");

        return providerReminderCallLog;
    }

    private AdherenceRecord createAdherenceRecord(String patientId, String district, java.sql.Date pillDate) {
        AdherenceRecord adherenceRecord = new AdherenceRecord();
        adherenceRecord.setDistrict(district);
        adherenceRecord.setPatientId(patientId);
        adherenceRecord.setPillDate(pillDate);
        adherenceRecord.setPillStatus("Taken");
        adherenceRecord.setTherapyId("therapyId");
        adherenceRecord.setTbId("tbId");
        return adherenceRecord;
    }

    public void createProvider(){
        provider = new Provider();
        provider.setDistrict("district");
        provider.setProviderId("providerId");
        providerRepository.save(provider);
    }

    public void createTestProvider(){
        testProvider = new Provider();
        testProvider.setDistrict("TestDistrict");
        testProvider.setProviderId("testProviderId");
        providerRepository.save(testProvider);
    }

    @After
    public void tearDown() {
        adherenceAuditLogRepository.deleteAll();
        adherenceRecordRepository.deleteAll();
        providerReminderCallLogRepository.deleteAll();
        patientRepository.deleteAll();
        providerRepository.deleteAll();
    }
}
