package org.motechproject.donotcall.dao;

import java.util.List;

import javax.sql.DataSource;

import org.motechproject.donotcall.contract.DoNotCallEntryContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author atish
 * Created a repository to fetch all records of FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL tables into DoNotCallEntryContract
 * Data Transfer Object. 
 */
@Repository
public class DoNotCallEntryDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL="select distinct entity_id, mobile_number from whp_reports.do_not_call where entity = 'Patient'";
	
	private static final String PATIENT_REMINDER_ALERT_FILTER = " and alert_type = 'PATIENT_REMINDER_ALERT'";
	
	private static final String PATIENT_IVR_ALERT_FILTER = " and alert_type = 'PATIENT_IVR_ALERT'";
	
	DoNotCallEntryDao(){
		
	}
	/**
	 * Injecting datasource
	 * @param dataSource
	 */
	@Autowired
	public DoNotCallEntryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    /**
     * Returing donotcall patients list filtered by AlertType Patient Reminder Alert.
     * @return {@link List}
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DoNotCallEntryContract> findDonotCallPatientByPatientReminderAlert(){
	 	return this.jdbcTemplate.query(FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL+PATIENT_REMINDER_ALERT_FILTER, new BeanPropertyRowMapper(DoNotCallEntryContract.class));
	}
	
    /**
     * Returing donotcall patients list filtered by Patient Ivr Alert.  
     * @return {@link List}
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DoNotCallEntryContract> findDonotCallPatientByPatientIvrAlert(){
	 	return this.jdbcTemplate.query(FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL+PATIENT_IVR_ALERT_FILTER, new BeanPropertyRowMapper(DoNotCallEntryContract.class));
	}

}
