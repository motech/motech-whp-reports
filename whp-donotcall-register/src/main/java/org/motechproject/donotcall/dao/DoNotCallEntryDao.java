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
	
	private static final String FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL="select entity_id, mobile_number from whp_reports.do_not_call where entity = 'Patient'";
	
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
     * Returing donotcall patients list. 
     * @return {@link List}
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DoNotCallEntryContract> findAllPatients(){
	 	return this.jdbcTemplate.query(FIND_ALL_PATIENT_REGISTERED_FOR_DO_NOT_CALL, new BeanPropertyRowMapper(DoNotCallEntryContract.class));
	}

}
