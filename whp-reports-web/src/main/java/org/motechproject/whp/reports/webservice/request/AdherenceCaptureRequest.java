package org.motechproject.whp.reports.webservice.request;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;

import java.io.Serializable;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@EqualsAndHashCode
public class AdherenceCaptureRequest implements Serializable {
    private Long timeTaken;
    private String channelId;
    private String status;
    private String patientId;
    private Integer submittedValue;
    private String providerId;
    private String submittedBy;
    private boolean valid;
    private String callId;

    public PatientAdherenceSubmission buildAdherenceSubmission() {
        PatientAdherenceSubmission submission = new PatientAdherenceSubmission();
        copyProperties(this, submission);
        return submission;
    }
}

