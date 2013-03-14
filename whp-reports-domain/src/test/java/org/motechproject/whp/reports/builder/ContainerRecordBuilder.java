package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.dimension.AlternateDiagnosis;
import org.motechproject.whp.reports.domain.dimension.ReasonForClosure;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails;

import java.sql.Timestamp;
import java.util.Date;

public class ContainerRecordBuilder {

    private ContainerRecord containerRecord;

    public ContainerRecordBuilder() {
        containerRecord = new ContainerRecord();
    }

    public ContainerRecord build() {
        return containerRecord;
    }

    public ContainerRecordBuilder withContainerId(String containerId) {
        containerRecord.setContainerId(containerId);
        return this;
    }

    public ContainerRecordBuilder withIssuedOnDate(Date dateIssuedOn) {
        containerRecord.setIssuedOn(new java.sql.Date(dateIssuedOn.getTime()));
        return this;
    }


    public ContainerRecordBuilder withIssuedOnSqlDate(java.sql.Date dateIssuedOn) {
        containerRecord.setIssuedOn(dateIssuedOn);
        return this;
    }

    public ContainerRecordBuilder withProviderId(String providerId) {
        containerRecord.setProviderId(providerId);
        return this;
    }

    public ContainerRecordBuilder withSubmittedBy(String submittedBy) {
        containerRecord.setSubmitterRole(submittedBy);
        return this;
    }

    public ContainerRecordBuilder withSubmitterId(String submitterId) {
        containerRecord.setSubmitterId(submitterId);
        return this;
    }

    public ContainerRecordBuilder withProviderDistrict(String location) {
        containerRecord.setProviderDistrict(location);
        return this;
    }

    public ContainerRecordBuilder withRegistrationInstance(String instance) {
        containerRecord.setRegistrationInstance(instance);
        return this;
    }

    public ContainerRecordBuilder withChannel(String channel) {
        containerRecord.setChannelId(channel);
        return this;
    }

    public ContainerRecordBuilder withPatientId(String patientId) {
        containerRecord.setPatientId(patientId);
        return this;
    }

    public ContainerRecordBuilder withStatus(String status) {
        containerRecord.setStatus(status);
        return this;
    }

    public ContainerRecordBuilder withReasonForClosureCode(String reasonForClosure) {
        containerRecord.setReasonForClosureCode(reasonForClosure);
        return this;
    }

    public ContainerRecordBuilder withReasonForClosure(String reasonForClosureCode, String reasonForClosureText) {
        containerRecord.setReasonForClosureCode(reasonForClosureCode);
        ReasonForClosure reasonForClosure = new ReasonForClosure();
        reasonForClosure.setCode(reasonForClosureCode);
        reasonForClosure.setText(reasonForClosureText);
        containerRecord.setReasonForClosure(reasonForClosure);
        return this;
    }

    public ContainerRecordBuilder withAlternateDiagnosisCode(String alternateDiagnosisCode) {
        containerRecord.setAlternateDiagnosisCode(alternateDiagnosisCode);
        return this;
    }

    public ContainerRecordBuilder withAlternateDiagnosis(String code, String name) {
        containerRecord.setAlternateDiagnosisCode(code);
        AlternateDiagnosis alternateDiagnosis = new AlternateDiagnosis();
        alternateDiagnosis.setCode(code);
        alternateDiagnosis.setText(name);
        containerRecord.setAlternateDiagnosis(alternateDiagnosis);
        return this;
    }

    public ContainerRecordBuilder withSmearTestResults(Date resultsDate1, String result1, Date resultsDate2, String result2) {
        containerRecord.setSmearTestDate1(new java.sql.Date(resultsDate1.getTime()));
        containerRecord.setSmearTestResult1(result1);
        containerRecord.setSmearTestDate2(new java.sql.Date(resultsDate2.getTime()));
        containerRecord.setSmearTestResult2(result2);
        return this;
    }

    public ContainerRecordBuilder withLabName(String labName) {
        containerRecord.setLabName(labName);
        return this;
    }

    public ContainerRecordBuilder withLabNumber(String labNumber) {
        containerRecord.setLabNumber(labNumber);
        return this;
    }

    public ContainerRecordBuilder withLabResultsCapturedOn(Date date) {
        containerRecord.setLabResultsCapturedOn(new Timestamp(date.getTime()));
        return this;
    }

    public ContainerRecordBuilder withDiagnosis(String diagnosis) {
        containerRecord.setDiagnosis(diagnosis);
        return this;
    }

    public ContainerRecordBuilder withClosureDate(Date date) {
        containerRecord.setClosureDate(new Timestamp(date.getTime()));
        return this;
    }

    public ContainerRecordBuilder withTbId(String tbID) {
        containerRecord.setTbId(tbID);
        return this;
    }

    public ContainerRecordBuilder withConsultationDate(Date consultationDate) {
        containerRecord.setConsultationDate(new java.sql.Date(consultationDate.getTime()));
        return this;
    }

    public ContainerRecordBuilder withMappingInstance(String mappingInstance) {
        containerRecord.setMappingInstance(mappingInstance);
        return this;
    }

    public ContainerRecordBuilder withDefaults() {
        long currentTimeInMillis = System.currentTimeMillis();
        this.withProviderId("providerId")
                .withSubmittedBy("CmfAdmin")
                .withIssuedOnSqlDate(new java.sql.Date(currentTimeInMillis))
                .withSubmitterId("admin")
                .withProviderDistrict("Patna")
                .withRegistrationInstance("Instance")
                .withChannel("IVR")
                .withPatientId("patient1")
                .withStatus("Close")
                .withReasonForClosureCode("0")
                .withAlternateDiagnosisCode("1334")
                .withClosureDate(new Date(currentTimeInMillis))
                .withConsultationDate(new Date(currentTimeInMillis))
                .withDiagnosis("positive")
                .withLabName("labName")
                .withLabNumber("labNumber")
                .withLabResultsCapturedOn(new Date(currentTimeInMillis))
                .withStatus("status")
                .withTbId("tbId")
                .withSmearTestResults(new Date(currentTimeInMillis), "result1", new Date(currentTimeInMillis), "result2")
                .withUserGivenPatientDetails("id", "name", 99, "gender");
        return this;
    }

    public ContainerRecordBuilder withUserGivenPatientDetails(String patientId, String patientName, int age, String gender) {
        UserGivenPatientDetails userGivenPatientDetails = new UserGivenPatientDetails();
        userGivenPatientDetails.setPatientId(patientId);
        userGivenPatientDetails.setPatientName(patientName);
        userGivenPatientDetails.setPatientAge(age);
        userGivenPatientDetails.setGender(gender);

        containerRecord.setUserGivenPatientDetails(userGivenPatientDetails);
        return  this;
    }
}
