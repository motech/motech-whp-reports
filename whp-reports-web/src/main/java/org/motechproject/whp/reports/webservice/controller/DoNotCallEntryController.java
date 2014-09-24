package org.motechproject.whp.reports.webservice.controller;

import java.util.List;

import org.motechproject.donotcall.contract.DoNotCallEntryContract;
import org.motechproject.whp.reports.service.DoNotCallEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created a controller which expose a web service
 * to be used by Whp
 * Requirement : To find all patient registered not to get a call from whp providers 
 * Injection Map : DoNotCallEntryService --> DoNotCallEntryRepository
 * @author atish
 *
 */
@Controller
@RequestMapping(value="/donotcall", produces=MediaType.APPLICATION_JSON_VALUE)
public class DoNotCallEntryController {
	

	private DoNotCallEntryService doNotCallEntryService;
	/**
	 * Injecting service
	 * @param doNotCallEntryService
	 */
	@Autowired
	public DoNotCallEntryController(DoNotCallEntryService doNotCallEntryService) {
	    this.doNotCallEntryService = doNotCallEntryService;
	}
	
	/**
	 * Controller 
	 * @return {@link List}
	 */
	@RequestMapping(value="/patients", method = RequestMethod.GET)
	@ResponseBody
	public List<DoNotCallEntryContract> showAllPatientOptedForDoNotCallOption() {
		return doNotCallEntryService.getAllPatients();
	}
	
}
