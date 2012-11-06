package org.motechproject.whp.reports.mapper;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;

import java.sql.Date;
import java.sql.Timestamp;

public class SputumTrackingRequestMapper {

    public static ContainerRecord buildContainerRegistrationRecord(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(containerRegistrationReportingRequest.getContainerId());
        containerRecord.setIssuedOn(getDateIfNotNull(containerRegistrationReportingRequest.getIssuedOn()));
        containerRecord.setProviderId(containerRegistrationReportingRequest.getProviderId());
        containerRecord.setSubmitterRole(containerRegistrationReportingRequest.getSubmitterRole());
        containerRecord.setSubmitterId(containerRegistrationReportingRequest.getSubmitterId());
        containerRecord.setInstance(containerRegistrationReportingRequest.getInstance());
        containerRecord.setChannelId(containerRegistrationReportingRequest.getChannelId());
        containerRecord.setDiagnosis(containerRegistrationReportingRequest.getDiagnosis());
        containerRecord.setLocationId(containerRegistrationReportingRequest.getLocationId());
        containerRecord.setStatus(containerRegistrationReportingRequest.getStatus());
        return containerRecord;
    }

    public static void populateSputumLabResults(SputumLabResultsCaptureReportingRequest request, ContainerRecord containerRecord) {

        containerRecord.setLabName(request.getLabName());
        containerRecord.setLabNumber(request.getLabNumber());
        containerRecord.setSmearTestDate1(getDateIfNotNull(request.getSmearTestDate1()));
        containerRecord.setSmearTestDate2(getDateIfNotNull(request.getSmearTestDate2()));
        containerRecord.setSmearTestResult1(request.getSmearTestResult1());
        containerRecord.setSmearTestResult2(request.getSmearTestResult2());
        containerRecord.setCumulativeResult(request.getCumulativeResult());
        containerRecord.setLabResultsCapturedOn(getDateTimeIfNotNull(request.getLabResultsCapturedOn()));
    }

    public static void updateContainerStatus(ContainerStatusReportingRequest containerStatusReportingRequest, ContainerRecord containerRecord) {
        containerRecord.setAlternateDiagnosisCode(containerStatusReportingRequest.getAlternateDiagnosisCode());
        containerRecord.setClosureDate(getDateTimeIfNotNull(containerStatusReportingRequest.getClosureDate()));
        containerRecord.setConsultationDate(getDateIfNotNull(containerStatusReportingRequest.getConsultationDate()));
        containerRecord.setReasonForClosure(containerStatusReportingRequest.getReasonForClosure());
        containerRecord.setStatus(containerStatusReportingRequest.getStatus());
        containerRecord.setDiagnosis(containerStatusReportingRequest.getDiagnosis());
    }

    public static void updateContainerPatientMapping(ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest, ContainerRecord containerRecord) {
        containerRecord.setPatientId(containerPatientMappingReportingRequest.getPatientId());
        containerRecord.setTbId(containerPatientMappingReportingRequest.getTbId());
        containerRecord.setMappingInstance(containerPatientMappingReportingRequest.getMappingInstance());
        containerRecord.setReasonForClosure(containerPatientMappingReportingRequest.getReasonForClosure());
        containerRecord.setStatus(containerPatientMappingReportingRequest.getStatus());
        containerRecord.setClosureDate(getDateTimeIfNotNull(containerPatientMappingReportingRequest.getClosureDate()));
        containerRecord.setConsultationDate(getDateIfNotNull(containerPatientMappingReportingRequest.getConsultationDate()));
        containerRecord.setDiagnosis(containerPatientMappingReportingRequest.getDiagnosis());
    }

    private static Date getDateIfNotNull(java.util.Date date) {
        if(date != null)
            return new Date(date.getTime());
        return null;
    }

    private static Timestamp getDateTimeIfNotNull(DateTime dateTime) {
        if(dateTime != null)
            return new Timestamp(dateTime.getMillis());
        return null;
    }
}
