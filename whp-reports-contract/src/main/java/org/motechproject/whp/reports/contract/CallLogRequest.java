package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CallLogRequest  implements Serializable {
    private String providerId;
    private Date startTime;
    private Date endTime;
    private String calledBy;
}
