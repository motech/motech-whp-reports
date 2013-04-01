package org.motechproject.donotcall.service;

import org.motechproject.couchdbcrud.service.JpaCrudEntity;
import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.motechproject.donotcall.repository.DoNotCallEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class DoNotCallCrudService extends JpaCrudEntity<DoNotCallEntry, Long>{

    @Autowired
    public DoNotCallCrudService(DoNotCallEntryRepository jpaRepository) {
        super(jpaRepository, Long.class);
    }

    @Override
    public List<String> getDisplayFields() {
        return asList("entityId", "mobileNumber");
    }

    @Override
    public List<String> getFilterFields() {
        return asList("entityId", "mobileNumber");
    }

    @Override
    public String getIdFieldName() {
        return "id";
    }

    @Override
    public Class getEntityType() {
        return DoNotCallEntry.class;
    }
}
