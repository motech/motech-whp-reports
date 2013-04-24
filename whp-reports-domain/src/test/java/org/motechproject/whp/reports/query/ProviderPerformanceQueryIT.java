package org.motechproject.whp.reports.query;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.repository.AdherenceAuditLogRepository;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

public class ProviderPerformanceQueryIT extends IntegrationTest{

    @Autowired
    AdherenceAuditLogRepository adherenceAuditLogRepository;

    @Autowired
    PatientRepository patientRepository;

    private final static String DISTRICT1 = "district1";
    private String DISTRICT2 = "district2";

    @Autowired
    private BigQueryService queryService;

    @Before
    public void setUp() {

        LocalDate currentDate = new LocalDate();

        Provider district1ProviderWithoutAdherence = createProvider("provider1", DISTRICT1);
        Provider district1ProviderWithAdherence = createProvider("provider2", DISTRICT1);
        Provider district1ProviderWithNoActivePatients = createProvider("provider3", DISTRICT1);
        Provider provider4FromDistrict2 = createProvider("provider4", DISTRICT2);
        Provider provider5FromDistrict2WithNoAdherence = createProvider("provider5", DISTRICT2);
        Provider provider6FromDistrict2WithNoAdherence = createProvider("provider6", DISTRICT2);

        LocalDate currentTreatmentWeek = currentDate.minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);

        Patient patientWithoutAdherence = createPatient("patient1", district1ProviderWithoutAdherence, currentTreatmentWeek.minusWeeks(2), null);
        Patient patientWithAdherence = createPatient("patient2", district1ProviderWithAdherence, currentTreatmentWeek.minusWeeks(2), null);
        Patient patientWithClosedTreatment = createPatient("patient3", district1ProviderWithNoActivePatients, currentTreatmentWeek.minusWeeks(2), currentTreatmentWeek.minusWeeks(1));
        Patient patientFromDistrict2 = createPatient("patient4", provider4FromDistrict2, currentTreatmentWeek.minusWeeks(3), null);
        Patient patient5FromDistrict2WithNoAdherence = createPatient("patient5", provider5FromDistrict2WithNoAdherence, currentTreatmentWeek.minusWeeks(7), null);
        Patient patient6FromDistrict2WithNoAdherence = createPatient("patient6", provider6FromDistrict2WithNoAdherence, currentTreatmentWeek.minusWeeks(8), currentTreatmentWeek);

        providerRepository.save(district1ProviderWithoutAdherence);
        providerRepository.save(district1ProviderWithAdherence);
        providerRepository.save(district1ProviderWithNoActivePatients);
        providerRepository.save(provider4FromDistrict2);
        providerRepository.save(provider5FromDistrict2WithNoAdherence);
        providerRepository.save(provider6FromDistrict2WithNoAdherence);

        patientRepository.save(patientWithoutAdherence);
        patientRepository.save(patientWithAdherence);
        patientRepository.save(patientWithClosedTreatment);
        patientRepository.save(patientFromDistrict2);
        patientRepository.save(patient5FromDistrict2WithNoAdherence);
        patientRepository.save(patient6FromDistrict2WithNoAdherence);

        AdherenceAuditLog auditLogForProviderWithAdherence1 = createAdherenceAuditLog(district1ProviderWithAdherence.getProviderId(), WHPDateTime.toSqlTimestamp(currentTreatmentWeek));
        AdherenceAuditLog auditLogForProviderWithAdherence2 = createAdherenceAuditLog(district1ProviderWithAdherence.getProviderId(), WHPDateTime.toSqlTimestamp(currentDate));
        AdherenceAuditLog auditLogForProviderWithoutAdherence = createAdherenceAuditLog(district1ProviderWithoutAdherence.getProviderId(), WHPDateTime.toSqlTimestamp(currentTreatmentWeek.minusWeeks(1)));
        AdherenceAuditLog auditLogForProviderFromDistrict2 = createAdherenceAuditLog(provider4FromDistrict2.getProviderId(), WHPDateTime.toSqlTimestamp(currentTreatmentWeek.minusWeeks(1)));
        AdherenceAuditLog auditLogForClosedPatient = createAdherenceAuditLog(district1ProviderWithNoActivePatients.getProviderId(), WHPDateTime.toSqlTimestamp(currentTreatmentWeek.minusWeeks(1)));

        adherenceAuditLogRepository.save(auditLogForProviderWithAdherence1);
        adherenceAuditLogRepository.save(auditLogForProviderWithAdherence2);
        adherenceAuditLogRepository.save(auditLogForProviderWithoutAdherence);
        adherenceAuditLogRepository.save(auditLogForProviderFromDistrict2);
        adherenceAuditLogRepository.save(auditLogForClosedPatient);
    }

    private Patient createPatient(String patientId, Provider provider, LocalDate treatmentWeekStartDate, LocalDate treatmentWeekEndDate) {
        return new PatientBuilder()
                .withDefaults()
                .withPatientId(patientId)
                .withProviderId(provider.getProviderId())
                .withDistrict(provider.getDistrict())
                .withTreatmentStartDate(toSqlDate(treatmentWeekStartDate))
                .withTreatmentEndDate(toSqlDate(treatmentWeekEndDate))
                .withTbRegistrationDate(toSqlDate(treatmentWeekStartDate))
                .build();
    }

    @Test
    public void shouldReturnNumberOfProvidersNotGivingAdherenceGroupedByWeeks(){

        QueryResult queryResult = queryService.executeQuery("provider.performance.by.district", new FilterParams());

        QueryResult expectedQueryResult = new QueryResultBuilder("district", "zero_week_bucket", "two_week_bucket", "three_to_five_week_bucket", "five_to_eight_week_bucket")
                .row(DISTRICT1, 1L, 2L, 0L, 0L)
                .row(DISTRICT2, 0L, 0L, 1L, 2L)
                .build();

        assertThat(queryResult.getContent().size(), is(2));
        assertThat(queryResult.getContent().get(0),is(expectedQueryResult.getContent().get(0)));
        assertThat(queryResult.getContent().get(1), is(expectedQueryResult.getContent().get(1)));
    }

    private AdherenceAuditLog createAdherenceAuditLog(String providerId, Timestamp creationTime) {
        AdherenceAuditLog auditLog = new AdherenceAuditLog();
        auditLog.setCreationTime(new Timestamp(creationTime.getTime()));
        auditLog.setProviderId(providerId);
        auditLog.setUserId(providerId);
        return auditLog;
    }

    @Override
    @After
    public void tearDown() {
        adherenceAuditLogRepository.deleteAll();
        patientRepository.deleteAll();
        providerRepository.deleteAll();
    }
}