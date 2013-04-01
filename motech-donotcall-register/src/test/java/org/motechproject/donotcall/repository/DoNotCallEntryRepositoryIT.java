package org.motechproject.donotcall.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.motechproject.donotcall.domain.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationDoNotCallRegisterContext.xml")
public class DoNotCallEntryRepositoryIT{

    @Autowired
    DoNotCallEntryRepository doNotCallEntryRepository;

    @Test
    public void shouldCreateDoNotCallEntry() throws InterruptedException {
        DoNotCallEntry entry = createDoNotCallEntry("patientId");

        DoNotCallEntry entryFromDB = doNotCallEntryRepository.findOne(entry.getId());
        Date lastUpdatedDate = entryFromDB.getUpdatedOn();


        assertThat(entryFromDB.getEntity(), is(entryFromDB.getEntity()));
        assertThat(entryFromDB.getEntityId(), is(entryFromDB.getEntityId()));
        assertThat(entryFromDB.getMobileNumber(), is(entryFromDB.getMobileNumber()));
        assertThat(entryFromDB.getUpdatedBy(), is(entryFromDB.getUpdatedBy()));
        assertThat(entryFromDB.getUpdatedOn(), notNullValue());

        Thread.sleep(1000);
        entry.setMobileNumber("1234567890");
        doNotCallEntryRepository.save(entry);


        DoNotCallEntry entryFromDBAfterUpdate = doNotCallEntryRepository.findOne(entry.getId());
        assertThat(entryFromDBAfterUpdate.getUpdatedOn(), greaterThan(lastUpdatedDate));
    }

    @Test
    public void shouldFindDoNotCallEntryByEntityId() {
        String patient1 = "patient1";
        DoNotCallEntry expectedEntry = createDoNotCallEntry(patient1);
        createDoNotCallEntry("patient2");

        List<DoNotCallEntry> doNotCallEntryList = doNotCallEntryRepository.findByEntityId(patient1);

        assertThat(doNotCallEntryList.size(), is(1));
        assertThat(doNotCallEntryList, hasItems(expectedEntry));
    }

    private DoNotCallEntry createDoNotCallEntry(String patientId) {
        DoNotCallEntry entry = new DoNotCallEntry();
        entry.setEntity(EntityType.Patient);
        entry.setEntityId(patientId);
        entry.setMobileNumber("mobileNumber");
        entry.setUpdatedBy("updatedBy");

        doNotCallEntryRepository.save(entry);
        return entry;
    }
}
