package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdherenceSubmissionRequest  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String providerId;
    private String submittedBy;
    private Date submissionDate;
    private boolean withinAdherenceWindow;
}
