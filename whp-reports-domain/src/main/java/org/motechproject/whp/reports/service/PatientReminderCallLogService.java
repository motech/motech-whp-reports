package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.calllog.PatientReminderCallLog;
import org.motechproject.whp.reports.repository.PatientReminderCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientReminderCallLogService {

	private PatientReminderCallLogRepository patientReminderCallLogRepository;
	
		PatientReminderCallLogService() {
	        //for spring proxy
	    }
	
	    @Autowired
	    PatientReminderCallLogService(PatientReminderCallLogRepository patientReminderCallLogRepository) {
	        this.patientReminderCallLogRepository = patientReminderCallLogRepository;
	    }
	
	    public void save(PatientReminderCallLog callLog) {
	    	try{
	    	patientReminderCallLogRepository.save(callLog);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    //TODO 

}
