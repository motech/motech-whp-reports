package org.motechproject.whp.reports.repository;

import java.util.List;

import org.motechproject.whp.reports.domain.measure.calllog.PatientReminderCallLog;


public interface PatientReminderCallLogRepository extends MotechJpaRepository<PatientReminderCallLog>{
	List<PatientReminderCallLog> findByPatientId(String patientId);
	
}
