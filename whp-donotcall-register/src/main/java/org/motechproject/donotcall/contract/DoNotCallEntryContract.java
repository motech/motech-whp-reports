package org.motechproject.donotcall.contract;

import javax.persistence.Column;
import lombok.Data;

/**
 * 
 * @author atish
 * Contract to be used for avoid sending alerts to donotcall patients
 * on Whp project.
 */

@Data
public class DoNotCallEntryContract {
	
	@Column(name="entity_id")
	private String entityId;
	
	@Column(name="mobile_number")
	private String mobileNumber;

}
