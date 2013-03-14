package org.motechproject.whp.reports.mapper;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
public class ContainerReportingRequestMapper {

    public  ContainerRecord mapContainerRecord(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(containerRegistrationReportingRequest.getContainerId());
        containerRecord.setIssuedOn(getDateIfNotNull(containerRegistrationReportingRequest.getIssuedOn()));
        containerRecord.setProviderId(containerRegistrationReportingRequest.getProviderId());
        containerRecord.setSubmitterRole(containerRegistrationReportingRequest.getSubmitterRole());
        containerRecord.setSubmitterId(containerRegistrationReportingRequest.getSubmitterId());
        containerRecord.setRegistrationInstance(containerRegistrationReportingRequest.getInstance());
        containerRecord.setChannelId(containerRegistrationReportingRequest.getChannelId());
        containerRecord.setDiagnosis(containerRegistrationReportingRequest.getDiagnosis());
        containerRecord.setProviderDistrict(containerRegistrationReportingRequest.getProviderDistrict());
        containerRecord.setStatus(containerRegistrationReportingRequest.getStatus());
        containerRecord.setCallId(containerRegistrationReportingRequest.getCallId());

        mapUserGivenPatientDetails(containerRegistrationReportingRequest, containerRecord);
        return containerRecord;
    }

    private void mapUserGivenPatientDetails(ContainerRegistrationReportingRequest request, ContainerRecord containerRecord) {
        UserGivenPatientDetails patientDetailsRecord = containerRecord.getUserGivenPatientDetails();
        org.motechproject.whp.reports.contract.UserGivenPatientDetails patientDetailsRequest = request.getUserGivenPatientDetails();
        patientDetailsRecord.setGender(patientDetailsRequest.getGender());
        patientDetailsRecord.setPatientId(patientDetailsRequest.getPatientId());
        patientDetailsRecord.setPatientAge(patientDetailsRequest.getPatientAge());
        patientDetailsRecord.setPatientName(patientDetailsRequest.getPatientName());
    }

    public  void populateSputumLabResults(SputumLabResultsCaptureReportingRequest request, ContainerRecord containerRecord) {

        containerRecord.setLabName(request.getLabName());
        containerRecord.setLabNumber(request.getLabNumber());
        containerRecord.setSmearTestDate1(getDateIfNotNull(request.getSmearTestDate1()));
        containerRecord.setSmearTestDate2(getDateIfNotNull(request.getSmearTestDate2()));
        containerRecord.setSmearTestResult1(request.getSmearTestResult1());
        containerRecord.setSmearTestResult2(request.getSmearTestResult2());
        containerRecord.setCumulativeResult(request.getCumulativeResult());
        containerRecord.setLabResultsCapturedOn(getDateTimeIfNotNull(request.getLabResultsCapturedOn()));
    }

    public  void updateContainerStatus(ContainerStatusReportingRequest containerStatusReportingRequest, ContainerRecord containerRecord) {
        containerRecord.setAlternateDiagnosisCode(containerStatusReportingRequest.getAlternateDiagnosisCode());
        containerRecord.setClosureDate(getDateTimeIfNotNull(containerStatusReportingRequest.getClosureDate()));
        containerRecord.setConsultationDate(getDateIfNotNull(containerStatusReportingRequest.getConsultationDate()));
        containerRecord.setReasonForClosureCode(containerStatusReportingRequest.getReasonForClosure());
        containerRecord.setStatus(containerStatusReportingRequest.getStatus());
        containerRecord.setDiagnosis(containerStatusReportingRequest.getDiagnosis());
    }

    public  void updateContainerPatientMapping(ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest, ContainerRecord containerRecord) {
        containerRecord.setPatientId(containerPatientMappingReportingRequest.getPatientId());
        containerRecord.setTbId(containerPatientMappingReportingRequest.getTbId());
        containerRecord.setMappingInstance(containerPatientMappingReportingRequest.getMappingInstance());
        containerRecord.setReasonForClosureCode(containerPatientMappingReportingRequest.getReasonForClosure());
        containerRecord.setStatus(containerPatientMappingReportingRequest.getStatus());
        containerRecord.setClosureDate(getDateTimeIfNotNull(containerPatientMappingReportingRequest.getClosureDate()));
        containerRecord.setConsultationDate(getDateIfNotNull(containerPatientMappingReportingRequest.getConsultationDate()));
        containerRecord.setDiagnosis(containerPatientMappingReportingRequest.getDiagnosis());
    }

    private  Date getDateIfNotNull(java.util.Date date) {
        if(date != null)
            return new Date(date.getTime());
        return null;
    }

    private  Timestamp getDateTimeIfNotNull(DateTime dateTime) {
        if(dateTime != null)
            return new Timestamp(dateTime.getMillis());
        return null;
    }
}
