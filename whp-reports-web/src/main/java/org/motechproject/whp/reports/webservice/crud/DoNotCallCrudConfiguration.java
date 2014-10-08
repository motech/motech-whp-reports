package org.motechproject.whp.reports.webservice.crud;

import org.motechproject.crud.builder.CrudModelBuilder;
import org.motechproject.crud.service.CrudEntity;
import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.motechproject.donotcall.repository.DoNotCallEntryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.motechproject.crud.builder.CrudEntityBuilder.newCrudEntity;

@Configuration
public class DoNotCallCrudConfiguration {

    @SuppressWarnings("unchecked")
	@Bean(name = "doNotCallEntryCrudEntity")
    public CrudEntity<DoNotCallEntry> build(DoNotCallEntryRepository doNotCallEntryRepository){
        return newCrudEntity(DoNotCallEntry.class)
                .jpaCrudRepository(doNotCallEntryRepository, Long.class)
                .model(CrudModelBuilder.jpaCrudModel()
                        .displayFields("entityId", "entity", "mobileNumber")
                        .hiddenFields("id", "updatedOn")
                        .filterFields("entityId")
                        .idFieldName("id"))
                .build();
    }
}
