package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.ContainerRecord;

import java.util.Date;

public class SputumTrackingBuilder {

    private ContainerRecord containerRecord;

    public SputumTrackingBuilder() {
        containerRecord = new ContainerRecord();
    }

    public ContainerRecord build() {
        return containerRecord;
    }

    public SputumTrackingBuilder withContainerId(String containerId) {
        containerRecord.setContainerId(containerId);
        return this;
    }

    public SputumTrackingBuilder issuedOn(Date dateIssuedOn) {
        containerRecord.setIssuedOn(new java.sql.Date(dateIssuedOn.getTime()));
        return this;
    }

    public SputumTrackingBuilder assignedToProvider(String providerId) {
        containerRecord.setProviderId(providerId);
        return this;
    }

    public SputumTrackingBuilder submittedBy(String submittedBy) {
        containerRecord.setSubmitterRole(submittedBy);
        return this;
    }

    public SputumTrackingBuilder havingSubmitterId(String submitterId) {
        containerRecord.setSubmitterId(submitterId);
        return this;
    }

    public SputumTrackingBuilder onLocationId(String location) {
        containerRecord.setLocationId(location);
        return this;
    }

    public SputumTrackingBuilder havingInstance(String instance) {
        containerRecord.setInstance(instance);
        return this;
    }

    public SputumTrackingBuilder submittedThroughChannel(String channel) {
        containerRecord.setChannelId(channel);
        return this;
    }

    public SputumTrackingBuilder forPatientId(String patientId) {
        containerRecord.setPatientId(patientId);
        return this;
    }

    public SputumTrackingBuilder havingStatus(String status) {
        containerRecord.setStatus(status);
        return this;
    }

    public SputumTrackingBuilder withReasonForClosure(String reasonForClosure) {
        containerRecord.setReasonForClosure(reasonForClosure);
        return this;
    }

    public SputumTrackingBuilder withAlternateDiagnosisCode(String alternateDiagnosisCode) {
        containerRecord.setAlternateDiagnosisCode(alternateDiagnosisCode);
        return this;
    }
}
