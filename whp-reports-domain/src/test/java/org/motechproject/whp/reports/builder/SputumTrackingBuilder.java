package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.SputumTrackingRecord;

import java.util.Date;

public class SputumTrackingBuilder {

    private SputumTrackingRecord sputumTrackingRecord;

    public SputumTrackingBuilder() {
        sputumTrackingRecord = new SputumTrackingRecord();
    }

    public SputumTrackingRecord build() {
        return sputumTrackingRecord;
    }

    public SputumTrackingBuilder withContainerId(String containerId) {
        sputumTrackingRecord.setContainerId(containerId);
        return this;
    }

    public SputumTrackingBuilder issuedOn(Date dateIssuedOn) {
        sputumTrackingRecord.setDateIssuedOn(dateIssuedOn);
        return this;
    }

    public SputumTrackingBuilder assignedToProvider(String providerId) {
        sputumTrackingRecord.setProviderId(providerId);
        return this;
    }

    public SputumTrackingBuilder submittedBy(String submittedBy) {
        sputumTrackingRecord.setSubmittedBy(submittedBy);
        return this;
    }

    public SputumTrackingBuilder havingSubmitterId(String submitterId) {
        sputumTrackingRecord.setSubmitterId(submitterId);
        return this;
    }

    public SputumTrackingBuilder onLocationId(String location) {
        sputumTrackingRecord.setLocationId(location);
        return this;
    }

    public SputumTrackingBuilder havingInstance(String instance) {
        sputumTrackingRecord.setInstance(instance);
        return this;
    }

    public SputumTrackingBuilder submittedThroughChannel(String channel) {
        sputumTrackingRecord.setChannelId(channel);
        return this;
    }

    public SputumTrackingBuilder forPatientId(String patientId) {
        sputumTrackingRecord.setPatientId(patientId);
        return this;
    }

    public SputumTrackingBuilder havingStatus(String status) {
        sputumTrackingRecord.setContainerStatus(status);
        return this;
    }

    public SputumTrackingBuilder withReasonForClosure(String reasonForClosure) {
        sputumTrackingRecord.setReasonForClosure(reasonForClosure);
        return this;
    }

    public SputumTrackingBuilder withAlternateDiagnosisCode(String alternateDiagnosisCode) {
        sputumTrackingRecord.setAlternateDiagnosisCode(alternateDiagnosisCode);
        return this;
    }
}
