package org.motechproject.whp.reports.builder;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.motechproject.whp.reports.domain.patient.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PatientBuilder {
    private Patient patient = new Patient();
    private Address patientAddress;
    private PatientAlerts patientAlerts = new PatientAlerts();
    private Therapy therapy;

    public PatientBuilder withDefaults(){
        defaultPatient();
        patientAddress = defaultPatientAddress();
        patientAlerts = defaultPatientAlerts();
        therapy = defaultTherapy();

        patient.setTherapies(asList(therapy));
        patient.setPatientAddress(patientAddress);
        patient.setPatientAlerts(patientAlerts);
        return this;
    }

    private void defaultPatient() {
        patient.setFirstName("firstName");
        patient.setLastName("lastName");
        patient.setGender("Male");
        patient.setOnActiveTreatment("Y");
        patient.setPatientId("patientid");
        patient.setPatientStatus("status");
        patient.setPhi("phi");
        patient.setPhoneNumber("phone");
    }

    private Therapy defaultTherapy() {
        Therapy therapy = new Therapy();
        therapy.setTherapyId("therapyId");
        therapy.setCloseDate(toSQLDate(new LocalDate()));
        therapy.setStartDate(toSQLDate(new LocalDate()));

        therapy.setTreatmentCategory("RNTCP Category 2");

        therapy.setCpStartDate(toSQLDate(new LocalDate()));
        therapy.setCpEndDate(toSQLDate(new LocalDate()));
        therapy.setCpPillsRemaining(4);
        therapy.setCpPillsTaken(10);
        therapy.setCpTotalDoses(20);

        therapy.setIpStartDate(toSQLDate(new LocalDate()));
        therapy.setIpEndDate(toSQLDate(new LocalDate()));
        therapy.setIpPillsRemaining(4);
        therapy.setIpPillsTaken(10);
        therapy.setIpTotalDoses(20);

        therapy.setEipStartDate(toSQLDate(new LocalDate()));
        therapy.setEipEndDate(toSQLDate(new LocalDate()));
        therapy.setEipPillsRemaining(4);
        therapy.setEipPillsTaken(10);
        therapy.setEipTotalDoses(20);

        therapy.setCreationDate(toSQLDate(new LocalDate()));
        therapy.setCurrentPhase("current");
        therapy.setCurrentTherapy("Y");
        therapy.setDiseaseClass("Disease");
        therapy.setPatientAge(12);
        therapy.setStatus("status");
        therapy.setTreatmentCategory("category1");

        therapy.setCumulativeMissedDoses(10);

        therapy.setTreatments(defaultTreatments());

        return therapy;
    }

    private List<Treatment> defaultTreatments() {
        List<Treatment> treatments  = new ArrayList<>();
        treatments.add(defaultTreatment());
        return treatments;
    }

    private Treatment defaultTreatment() {
        Treatment treatment = new Treatment();
        treatment.setCurrentTreatment("Y");
        treatment.setEndDate(toSQLDate(new LocalDate().minusDays(2)));
        treatment.setStartDate(toSQLDate(new LocalDate().minusDays(4)));
        treatment.setIsPaused("Y");
        treatment.setPatientType("type");
        treatment.setPausedDate(toSQLDate(new LocalDate()));
        treatment.setPreTreatmentSmearTestResult("Positive");
        treatment.setPreTreatmentWeight(80.0);
        treatment.setProviderDistrict("Begusarai");
        treatment.setProviderId("provider");
        treatment.setReasonsForPause("A reason to remember");
        treatment.setTbRegistrationNumber("tbRegNumber");
        treatment.setTotalPausedDuration(10);
        treatment.setTreatmentOutcome("treatment outcome");

        return treatment;
    }

    private PatientAlerts defaultPatientAlerts() {
        PatientAlerts patientAlerts = new PatientAlerts();
        patientAlerts.setAdherenceMissingWeeks(1);
        patientAlerts.setAdherenceMissingWeeksAlertSeverity(1);
        patientAlerts.setAdherenceMissingWeeksAlertDate(toSQLDate(new LocalDate()));
        patientAlerts.setCumulativeMissedDoses(1);
        patientAlerts.setCumulativeMissedDosesAlertSeverity(1);
        patientAlerts.setCumulativeMissedDosesAlertDate(toSQLDate(new LocalDate()));
        patientAlerts.setTreatmentNotStarted(1);
        patientAlerts.setTreatmentNotStartedAlertSeverity(1);
        patientAlerts.setTreatmentNotStartedAlertDate(toSQLDate(new LocalDate()));

        return patientAlerts;
    }

    private Address defaultPatientAddress() {
        Address patientAddress = new Address();
        patientAddress.setBlock("block");
        patientAddress.setDistrict("district");
        patientAddress.setLandmark("landmark");
        patientAddress.setVillage("village");
        patientAddress.setState("state");
        patientAddress.setLocation("location");

        return patientAddress;
    }

    public Patient build(){
        return patient;
    }

    public PatientBuilder withPatientId(String patientId) {
        patient.setPatientId(patientId);
        return this;
    }

    private Date toSQLDate(LocalDate date) {
        return new Date(DateTimeZone.UTC.convertUTCToLocal(date.toDate().getTime()));
    }

    public PatientBuilder withDistrict(String newDistrict) {
        getCurrentTreatment().setProviderDistrict(newDistrict);
        return this;
    }

    private Treatment getCurrentTreatment() {
        return patient.getTherapies().get(0).getTreatments().get(0);
    }

    public PatientBuilder withTbRegistrationDate(LocalDate date) {
        getCurrentTreatment().setStartDate(toSQLDate(date));
        return this;
    }

    public PatientBuilder withTbRegistrationDate(Date date) {
        therapy.setStartDate(date);
        return this;
    }

    public PatientBuilder withProviderId(String providerId) {
        getCurrentTreatment().setProviderId(providerId);
        return this;
    }

    public PatientBuilder withTreatmentStartDate(Date date) {
        getCurrentTreatment().setStartDate(date);
        return this;
    }

    public PatientBuilder withTreatmentEndDate(Date endDate) {
        getCurrentTreatment().setEndDate(endDate);
        return this;
    }

    public PatientBuilder withAdherenceMissingWeeks(int missingWeeks) {
        patientAlerts.setAdherenceMissingWeeks(missingWeeks);
        return this;
    }

    public PatientBuilder withMobileNumber(String mobileNumber) {
        patient.setPhoneNumber(mobileNumber);
        return this;
    }

    public PatientBuilder withInactiveStatus() {
        patient.setOnActiveTreatment("N");
        return this;
    }
}
