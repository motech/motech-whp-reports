package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;

public class PatientAdherenceSubmissionBuilder {

    private String patientId;
    private long inSeconds;
    private String source;
    private String status;
    private String user;
    private String providerId;
    private String callId;
    private boolean valid;

    public static PatientAdherenceSubmissionBuilder newSubmission() {
        return new PatientAdherenceSubmissionBuilder();
    }

    public PatientAdherenceSubmissionBuilder forPatient(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public PatientAdherenceSubmissionBuilder withDuration(long inSeconds) {
        this.inSeconds = inSeconds;
        return this;
    }

    public PatientAdherenceSubmissionBuilder through(String source) {
        this.source = source;
        return this;
    }

    public PatientAdherenceSubmissionBuilder as(String status) {
        this.status = status;
        return this;
    }

    public PatientAdherenceSubmissionBuilder by(String user) {
        this.user = user;
        return this;
    }

    public PatientAdherenceSubmissionBuilder withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public PatientAdherenceSubmissionBuilder onCall(String callId) {
        this.callId = callId;
        return this;
    }

    public PatientAdherenceSubmissionBuilder isValid(boolean valid) {
        this.valid = valid;
        return this;
    }

    public PatientAdherenceSubmission build() {
        PatientAdherenceSubmission submission = new PatientAdherenceSubmission();
        submission.setChannelId(source);
        submission.setPatientId(patientId);
        submission.setTimeTaken(inSeconds);
        submission.setCallId(callId);
        submission.setProviderId(providerId);
        submission.setStatus(status);
        submission.setValid(valid);
        submission.setSubmittedBy(user);
        return submission;
    }
}
