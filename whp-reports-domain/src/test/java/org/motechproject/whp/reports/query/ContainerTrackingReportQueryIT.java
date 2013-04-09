package org.motechproject.whp.reports.query;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.dimension.AlternateDiagnosis;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.domain.dimension.ReasonForClosure;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails;
import org.motechproject.whp.reports.repository.AlternateDiagnosisRepository;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.motechproject.whp.reports.repository.ReasonForClosureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlDate;

public class ContainerTrackingReportQueryIT extends IntegrationTest {

    ReasonForClosure reasonForClosure = new ReasonForClosure("rc", "reasonText");
    AlternateDiagnosis alternateDiagnosis = new AlternateDiagnosis("ac", "alternateDiagnosisText");

    @Autowired
    BigQueryService bigQueryService;

    @Autowired
    private ContainerRecordRepository containerRecordRepository;
    @Autowired
    private AlternateDiagnosisRepository alternateDiagnosisRepository;
    @Autowired
    private ReasonForClosureRepository reasonForClosureRepository;

    Provider testProvider;

    @Test
    public void shouldFetchResult(){
        createTestProvider();

        alternateDiagnosisRepository.save(alternateDiagnosis);
        reasonForClosureRepository.save(reasonForClosure);

        ContainerRecord containerRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(toSqlDate(DateTime.now()))
                .withContainerId("container").build();

        ContainerRecord testContainerRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(toSqlDate(DateTime.now()))
                .withContainerId("testContainer").build();
        testContainerRecord.setProviderId(testProvider.getProviderId());
        testContainerRecord.setProviderDistrict(testProvider.getDistrict());

        containerRecordRepository.save(asList(containerRecord, testContainerRecord));
        QueryResult queryResult = bigQueryService.executeQuery("containerReport", new FilterParams());
        assertThat(queryResult.getContent().size(), is(1));

        Map<String,Object> containerRecordResult = queryResult.getContent().get(0);
        assertThat((String) containerRecordResult.get("patient_id"), is(containerRecord.getPatientId()));
        assertThat((String) containerRecordResult.get("reason_for_closure"), is(reasonForClosure.getText()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_code"), is(containerRecord.getAlternateDiagnosisCode()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_name"), is(alternateDiagnosis.getText()));
        assertThat((String) containerRecordResult.get("channel_id"), is(containerRecord.getChannelId()));
        assertThat((Timestamp) containerRecordResult.get("closure_date"), is(containerRecord.getClosureDate()));
        assertThat((Date) containerRecordResult.get("consultation_date"), is(getSqlDate(containerRecord.getConsultationDate())));
        assertThat((String) containerRecordResult.get("container_id"), is(containerRecord.getContainerId()));
        assertThat((Date) containerRecordResult.get("date_issued_on"), is(getSqlDate(containerRecord.getIssuedOn())));
        assertThat((String) containerRecordResult.get("diagnosis"), is(containerRecord.getDiagnosis()));
        assertThat((String) containerRecordResult.get("lab_name"), is(containerRecord.getLabName()));
        assertThat((String) containerRecordResult.get("lab_number"), is(containerRecord.getLabNumber()));
        assertThat((Timestamp) containerRecordResult.get("lab_results_captured_on"), is(containerRecord.getLabResultsCapturedOn()));
        assertThat((String) containerRecordResult.get("mapping_instance"), is(containerRecord.getMappingInstance()));
        assertThat((String) containerRecordResult.get("provider_district"), is(containerRecord.getProviderDistrict()));
        assertThat((String) containerRecordResult.get("provider_id"), is(containerRecord.getProviderId()));
        assertThat((String) containerRecordResult.get("registration_instance"), is(containerRecord.getRegistrationInstance()));
        assertThat((Date) containerRecordResult.get("smear_test_date_1"), is(getSqlDate(containerRecord.getSmearTestDate1())));
        assertThat((Date) containerRecordResult.get("smear_test_date_2"), is(getSqlDate(containerRecord.getSmearTestDate2())));
        assertThat((String) containerRecordResult.get("smear_test_result_1"), is(containerRecord.getSmearTestResult1()));
        assertThat((String) containerRecordResult.get("smear_test_result_2"), is(containerRecord.getSmearTestResult2()));
        assertThat((String) containerRecordResult.get("status"), is(containerRecord.getStatus()));
        assertThat((String) containerRecordResult.get("submitter_id"), is(containerRecord.getSubmitterId()));
        assertThat((String) containerRecordResult.get("tb_id"), is(containerRecord.getTbId()));
        assertUserGivenDetails(containerRecordResult, containerRecord);

    }


    @Test
    public void shouldFetchResultByDistrict(){

        alternateDiagnosisRepository.save(alternateDiagnosis);
        reasonForClosureRepository.save(reasonForClosure);

        ContainerRecord containerRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(toSqlDate(DateTime.now()))
                .withContainerId("container").build();
        containerRecord.setProviderDistrict("Patna");

        ContainerRecord containerRecord1 = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(toSqlDate(DateTime.now()))
                .withContainerId("testContainer").build();
        containerRecord1.setProviderDistrict("district1");

        containerRecordRepository.save(asList(containerRecord, containerRecord1));
        FilterParams params = new FilterParams();
        params.put("district", "Patna");

        QueryResult queryResult = bigQueryService.executeQuery("containerReport", params);
        assertThat(queryResult.getContent().size(), is(1));

        Map<String,Object> containerRecordResult = queryResult.getContent().get(0);
        assertThat((String) containerRecordResult.get("patient_id"), is(containerRecord.getPatientId()));
        assertThat((String) containerRecordResult.get("reason_for_closure"), is(reasonForClosure.getText()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_code"), is(containerRecord.getAlternateDiagnosisCode()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_name"), is(alternateDiagnosis.getText()));
        assertThat((String) containerRecordResult.get("channel_id"), is(containerRecord.getChannelId()));
        assertThat((Timestamp) containerRecordResult.get("closure_date"), is(containerRecord.getClosureDate()));
        assertThat((Date) containerRecordResult.get("consultation_date"), is(getSqlDate(containerRecord.getConsultationDate())));
        assertThat((String) containerRecordResult.get("container_id"), is(containerRecord.getContainerId()));
        assertThat((Date) containerRecordResult.get("date_issued_on"), is(getSqlDate(containerRecord.getIssuedOn())));
        assertThat((String) containerRecordResult.get("diagnosis"), is(containerRecord.getDiagnosis()));
        assertThat((String) containerRecordResult.get("lab_name"), is(containerRecord.getLabName()));
        assertThat((String) containerRecordResult.get("lab_number"), is(containerRecord.getLabNumber()));
        assertThat((Timestamp) containerRecordResult.get("lab_results_captured_on"), is(containerRecord.getLabResultsCapturedOn()));
        assertThat((String) containerRecordResult.get("mapping_instance"), is(containerRecord.getMappingInstance()));
        assertThat((String) containerRecordResult.get("provider_district"), is(containerRecord.getProviderDistrict()));
        assertThat((String) containerRecordResult.get("provider_id"), is(containerRecord.getProviderId()));
        assertThat((String) containerRecordResult.get("registration_instance"), is(containerRecord.getRegistrationInstance()));
        assertThat((Date) containerRecordResult.get("smear_test_date_1"), is(getSqlDate(containerRecord.getSmearTestDate1())));
        assertThat((Date) containerRecordResult.get("smear_test_date_2"), is(getSqlDate(containerRecord.getSmearTestDate2())));
        assertThat((String) containerRecordResult.get("smear_test_result_1"), is(containerRecord.getSmearTestResult1()));
        assertThat((String) containerRecordResult.get("smear_test_result_2"), is(containerRecord.getSmearTestResult2()));
        assertThat((String) containerRecordResult.get("status"), is(containerRecord.getStatus()));
        assertThat((String) containerRecordResult.get("submitter_id"), is(containerRecord.getSubmitterId()));
        assertThat((String) containerRecordResult.get("tb_id"), is(containerRecord.getTbId()));
        assertUserGivenDetails(containerRecordResult, containerRecord);
    }

    @Test
    public void shouldFetchResultByToAndFromDate(){

        alternateDiagnosisRepository.save(alternateDiagnosis);
        reasonForClosureRepository.save(reasonForClosure);

        ContainerRecord containerRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(new LocalDate(2013, 3, 9).toDate())
                .withContainerId("container").build();
        containerRecord.setProviderDistrict("Patna");

        ContainerRecord pastRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(new LocalDate(2013, 1, 9).toDate())
                .withContainerId("testContainer").build();
        pastRecord.setProviderDistrict("district1");

        ContainerRecord futureRecord = new ContainerRecordBuilder().withDefaults()
                .withAlternateDiagnosisCode(alternateDiagnosis.getCode())
                .withReasonForClosureCode(reasonForClosure.getCode())
                .withIssuedOnDate(new LocalDate(2013, 6, 10).toDate())
                .withContainerId("testContainer").build();
        futureRecord.setProviderDistrict("district1");

        containerRecordRepository.save(asList(containerRecord, pastRecord, futureRecord));
        FilterParams params = new FilterParams();
        params.put("from_date", "09/02/2013");
        params.put("to_date", "09/06/2013");

        QueryResult queryResult = bigQueryService.executeQuery("containerReport", params);
        assertThat(queryResult.getContent().size(), is(1));

        Map<String,Object> containerRecordResult = queryResult.getContent().get(0);
        assertThat((String) containerRecordResult.get("patient_id"), is(containerRecord.getPatientId()));
        assertThat((String) containerRecordResult.get("reason_for_closure"), is(reasonForClosure.getText()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_code"), is(containerRecord.getAlternateDiagnosisCode()));
        assertThat((String) containerRecordResult.get("alternate_diagnosis_name"), is(alternateDiagnosis.getText()));
        assertThat((String) containerRecordResult.get("channel_id"), is(containerRecord.getChannelId()));
        assertThat((Timestamp) containerRecordResult.get("closure_date"), is(containerRecord.getClosureDate()));
        assertThat((Date) containerRecordResult.get("consultation_date"), is(getSqlDate(containerRecord.getConsultationDate())));
        assertThat((String) containerRecordResult.get("container_id"), is(containerRecord.getContainerId()));
        assertThat((Date) containerRecordResult.get("date_issued_on"), is(getSqlDate(containerRecord.getIssuedOn())));
        assertThat((String) containerRecordResult.get("diagnosis"), is(containerRecord.getDiagnosis()));
        assertThat((String) containerRecordResult.get("lab_name"), is(containerRecord.getLabName()));
        assertThat((String) containerRecordResult.get("lab_number"), is(containerRecord.getLabNumber()));
        assertThat((Timestamp) containerRecordResult.get("lab_results_captured_on"), is(containerRecord.getLabResultsCapturedOn()));
        assertThat((String) containerRecordResult.get("mapping_instance"), is(containerRecord.getMappingInstance()));
        assertThat((String) containerRecordResult.get("provider_district"), is(containerRecord.getProviderDistrict()));
        assertThat((String) containerRecordResult.get("provider_id"), is(containerRecord.getProviderId()));
        assertThat((String) containerRecordResult.get("registration_instance"), is(containerRecord.getRegistrationInstance()));
        assertThat((Date) containerRecordResult.get("smear_test_date_1"), is(getSqlDate(containerRecord.getSmearTestDate1())));
        assertThat((Date) containerRecordResult.get("smear_test_date_2"), is(getSqlDate(containerRecord.getSmearTestDate2())));
        assertThat((String) containerRecordResult.get("smear_test_result_1"), is(containerRecord.getSmearTestResult1()));
        assertThat((String) containerRecordResult.get("smear_test_result_2"), is(containerRecord.getSmearTestResult2()));
        assertThat((String) containerRecordResult.get("status"), is(containerRecord.getStatus()));
        assertThat((String) containerRecordResult.get("submitter_id"), is(containerRecord.getSubmitterId()));
        assertThat((String) containerRecordResult.get("tb_id"), is(containerRecord.getTbId()));
        assertUserGivenDetails(containerRecordResult, containerRecord);
    }

    private void assertUserGivenDetails(Map<String, Object> containerRecordResult, ContainerRecord containerRecord) {
        UserGivenPatientDetails expectedDetails = containerRecord.getUserGivenPatientDetails();
        assertThat((String) containerRecordResult.get("given_patient_name"), is(expectedDetails.getPatientName()));
        assertThat((String) containerRecordResult.get("given_patient_id"), is(expectedDetails.getPatientId()));
        assertThat((BigDecimal) containerRecordResult.get("given_patient_age"), is(new BigDecimal(expectedDetails.getPatientAge())));
        assertThat((String) containerRecordResult.get("given_gender"), is(expectedDetails.getGender()));
    }

    private java.sql.Date getSqlDate(java.sql.Date date) {
        return new java.sql.Date(new LocalDate(date.getTime()).toDate().getTime());
    }

    public void createTestProvider(){
        testProvider = new Provider();
        testProvider.setDistrict("TestDistrict");
        testProvider.setProviderId("testProviderId");
        providerRepository.save(testProvider);
    }

    @After
    public void tearDown() {
        containerRecordRepository.deleteAll();
        alternateDiagnosisRepository.delete(alternateDiagnosis);
        reasonForClosureRepository.delete(reasonForClosure);
        providerRepository.deleteAll();
    }

}
