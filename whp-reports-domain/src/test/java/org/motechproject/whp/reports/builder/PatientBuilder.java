package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.patient.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PatientBuilder {
    private Patient patient = new Patient();
    private Address patientAddress;
    private PatientAlerts patientAlerts;
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
        therapy.setCloseDate(new Date(System.currentTimeMillis()));
        therapy.setStartDate(new Date(System.currentTimeMillis()));

        therapy.setCpStartDate(new Date(System.currentTimeMillis()));
        therapy.setCpEndDate(new Date(System.currentTimeMillis()));
        therapy.setCpPillsRemaining(4);
        therapy.setCpPillsTaken(10);
        therapy.setCpTotalDoses(20);

        therapy.setIpStartDate(new Date(System.currentTimeMillis()));
        therapy.setIpEndDate(new Date(System.currentTimeMillis()));
        therapy.setIpPillsRemaining(4);
        therapy.setIpPillsTaken(10);
        therapy.setIpTotalDoses(20);

        therapy.setEipStartDate(new Date(System.currentTimeMillis()));
        therapy.setEipEndDate(new Date(System.currentTimeMillis()));
        therapy.setEipPillsRemaining(4);
        therapy.setEipPillsTaken(10);
        therapy.setEipTotalDoses(20);

        therapy.setCreationDate(new Date(System.currentTimeMillis()));
        therapy.setCurrentPhase("current");
        therapy.setCurrentTherapy("Y");
        therapy.setDiseaseClass("Disease");
        therapy.setPatientAge(12);
        therapy.setStatus("status");
        therapy.setTreatmentCategory("category1");

        therapy.setTreatments(defaultTreatments());

        return therapy;
    }

    private List<Treatment> defaultTreatments() {
        List<Treatment> treatments  = new ArrayList<>();
        treatments.add(defaultTreatment("treatment1"));
        treatments.add(defaultTreatment("treatment2"));
        return treatments;
    }

    private Treatment defaultTreatment(String treatmentId) {
        Treatment treatment = new Treatment();
        treatment.setCurrentTreatment("Y");
        treatment.setEndDate(new Date(System.currentTimeMillis()));
        treatment.setStartDate(new Date(System.currentTimeMillis()));
        treatment.setIsPaused("Y");
        treatment.setPatientType("type");
        treatment.setPausedDate(new Date(System.currentTimeMillis()));
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
        patientAlerts.setAdherenceMissingWeeksAlertDate(new Date(System.currentTimeMillis()));
        patientAlerts.setCumulativeMissedDoses(1);
        patientAlerts.setCumulativeMissedDosesAlertSeverity(1);
        patientAlerts.setCumulativeMissedDosesAlertDate(new Date(System.currentTimeMillis()));
        patientAlerts.setTreatmentNotStarted(1);
        patientAlerts.setTreatmentNotStartedAlertSeverity(1);
        patientAlerts.setTreatmentNotStartedAlertDate(new Date(System.currentTimeMillis()));

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
}
