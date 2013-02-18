package org.motechproject.whp.reports.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PatientRepositoryIT  extends IntegrationTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionTemplate transactionTemplate;

    @Before
    public void setUp() throws Exception {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Test
    public void shouldCreatePatient() {
        final Patient patient = new PatientBuilder().withDefaults().build();

        patientRepository.save(patient);

        final Long patientId = patient.getId();
        assertNotNull(patientId);

        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                Patient patientFromDB = patientRepository.findOne(patientId);
                assertNotNull(patientFromDB.getTherapies().get(0).getId());
                assertNotNull(patientFromDB.getTherapies().get(0).getTreatments().get(0).getId());
                assertNotNull(patientFromDB.getId());
                assertNotNull(patientFromDB.getPatientAddress().getId());
                assertNotNull(patientFromDB.getPatientAlerts().getId());
                return null;
            }
        });
    }

    @Test
    @Transactional
    public void shouldReturnPatientByPatientId() {
        String patientId = "patientId";
        Patient patient = new PatientBuilder().withDefaults().withPatientId(patientId).build();

        patientRepository.save(patient);

        Patient patientFromDB = patientRepository.findByPatientId(patientId);

        assertNotNull(patientFromDB);
        assertEquals(patient.getId(), patientFromDB.getId());
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}
