package org.motechproject.whp.reports.service;

import java.util.List;

import org.motechproject.donotcall.contract.DoNotCallEntryContract;
import org.motechproject.donotcall.dao.DoNotCallEntryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created a service which will be exposed to controller
 * It Contains a getAll method which fetch all registers of
 * DoNotCallEntryContract record
 * Injection Path: -> DoNotCallEntryDao
 * @author atish
 *
 */
@Service
@Transactional(readOnly=true)
public class DoNotCallEntryService {
	
	private DoNotCallEntryDao doNotCallEntryDao;
	
	public DoNotCallEntryService() {
	
	}
	
	/**
	 * Injecting dao
	 * @param doNotCallEntryDao
	 */
	@Autowired
	public DoNotCallEntryService(DoNotCallEntryDao doNotCallEntryDao){
		this.doNotCallEntryDao = doNotCallEntryDao;
	}

	/**
	 * Returing donotcall patients
	 * @return {@link List}
	 */
	public List<DoNotCallEntryContract> getAllPatients(){
		return doNotCallEntryDao.findAllPatients();
	}

}
