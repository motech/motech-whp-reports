package org.motechproject.whp.reports.webservice.controller;

import org.junit.Test;
import org.motechproject.crud.builder.CrudModelBuilder;
import org.motechproject.crud.model.CrudModel;
import org.motechproject.crud.service.CrudEntity;
import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.motechproject.donotcall.repository.DoNotCallEntryRepository;
import org.motechproject.whp.reports.webservice.crud.DoNotCallCrudConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class DoNotCallCrudConfigurationTest {

    @Test
    public void shouldCreateDoNotCallCrudEntity() {
        DoNotCallEntryRepository doNotCallEntryRepository = mock(DoNotCallEntryRepository.class);
        CrudEntity<DoNotCallEntry> doNotCallEntryCrudEntity = new DoNotCallCrudConfiguration().build(doNotCallEntryRepository);
        CrudModel expectedModel = CrudModelBuilder.jpaCrudModel()
                .displayFields("entityId", "entity", "mobileNumber")
                .hiddenFields("id", "updatedOn")
                .filterFields("entityId")
                .displayName("Do Not Call Entry")
                .idFieldName("id").build();

        assertEquals(expectedModel, doNotCallEntryCrudEntity.getModel());
    }
}
