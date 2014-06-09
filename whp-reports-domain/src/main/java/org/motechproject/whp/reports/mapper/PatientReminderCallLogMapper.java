package org.motechproject.whp.reports.mapper;

import static org.motechproject.whp.reports.date.WHPDateTime.timestamp;

import org.apache.commons.lang.StringUtils;
import org.motechproject.whp.reports.contract.PatientReminderCallLogRequest;
import org.motechproject.whp.reports.domain.measure.calllog.PatientReminderCallLog;
import org.springframework.stereotype.Component;
@Component
public class PatientReminderCallLogMapper {

	   public PatientReminderCallLog map(PatientReminderCallLogRequest request) {
		   PatientReminderCallLog callLog = new PatientReminderCallLog();
	        callLog.setAttempt(Integer.parseInt(request.getAttempt()));
	        callLog.setAttemptTime(timestamp(request.getAttemptTime()));
	        callLog.setStartTime(timestamp(request.getStartTime()));
	        callLog.setEndTime(timestamp(request.getEndTime()));
	        callLog.setCallId(request.getCallId());
	        callLog.setCallAnswered(request.getCallAnswered());
	        callLog.setDisconnectionType(request.getDisconnectionType());
	        callLog.setReminderType(request.getReminderType());
	        callLog.setMobileNumber(StringUtils.right(request.getMsisdn(), 10));
	        callLog.setRequestId(request.getRequestId());
	        callLog.setPatientId(request.getPatientId());
	        return callLog;
	    }
}
