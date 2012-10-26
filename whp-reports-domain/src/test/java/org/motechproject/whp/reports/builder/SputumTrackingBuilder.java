package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.SputumTracking;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

public class SputumTrackingBuilder {

    private SputumTracking sputumTracking;

    public SputumTrackingBuilder() {
        sputumTracking = new SputumTracking();
    }

    public SputumTracking build() {
        return sputumTracking;
    }

    public SputumTrackingBuilder withContainerId(String containerId) {
        sputumTracking.setContainerId(containerId);
        return this;
    }

    public SputumTrackingBuilder issuedOn(Date dateIssuedOn) {
        sputumTracking.setDateIssuedOn(dateIssuedOn);
        return this;
    }

    public SputumTrackingBuilder assignedToProvider(String providerId) {
        sputumTracking.setProviderId(providerId);
        return this;
    }

    public SputumTrackingBuilder submittedBy(String submittedBy) {
        sputumTracking.setSubmittedBy(submittedBy);
        return this;
    }

    public SputumTrackingBuilder havingSubmitterId(String submitterId) {
        sputumTracking.setSubmitterId(submitterId);
        return this;
    }

    public SputumTrackingBuilder onLocationId(String location) {
        sputumTracking.setLocationId(location);
        return this;
    }

    public SputumTrackingBuilder havingInstance(String instance) {
        sputumTracking.setInstance(instance);
        return this;
    }

    public SputumTrackingBuilder submittedThroughChannel(String channel) {
        sputumTracking.setChannelId(channel);
        return this;
    }

    public SputumTrackingBuilder forPatientId(String patientId) {
        sputumTracking.setPatientId(patientId);
        return this;
    }

    public SputumTrackingBuilder havingStatus(String status) {
        sputumTracking.setContainerStatus(status);
        return this;
    }

    public SputumTrackingBuilder withReasonForClosure(String reasonForClosure) {
        sputumTracking.setReasonForClosure(reasonForClosure);
        return this;
    }

    public SputumTrackingBuilder withAlternateDiagnosisCode(String alternateDiagnosisCode) {
        sputumTracking.setAlternateDiagnosisCode(alternateDiagnosisCode);
        return this;
    }
}
