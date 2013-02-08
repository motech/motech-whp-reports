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
        therapy.setCloseDate(today());
        therapy.setStartDate(today());

        therapy.setCpStartDate(today());
        therapy.setCpEndDate(today());
        therapy.setCpPillsRemaining(4);
        therapy.setCpPillsTaken(10);
        therapy.setCpTotalDoses(20);

        therapy.setIpStartDate(today());
        therapy.setIpEndDate(today());
        therapy.setIpPillsRemaining(4);
        therapy.setIpPillsTaken(10);
        therapy.setIpTotalDoses(20);

        therapy.setEipStartDate(today());
        therapy.setEipEndDate(today());
        therapy.setEipPillsRemaining(4);
        therapy.setEipPillsTaken(10);
        therapy.setEipTotalDoses(20);

        therapy.setCreationDate(today());
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
        treatment.setEndDate(today());
        treatment.setStartDate(today());
        treatment.setIsPaused("Y");
        treatment.setPatientType("type");
        treatment.setPausedDate(today());
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
        patientAlerts.setAdherenceMissingWeeksAlertDate(today());
        patientAlerts.setCumulativeMissedDoses(1);
        patientAlerts.setCumulativeMissedDosesAlertSeverity(1);
        patientAlerts.setCumulativeMissedDosesAlertDate(today());
        patientAlerts.setTreatmentNotStarted(1);
        patientAlerts.setTreatmentNotStartedAlertSeverity(1);
        patientAlerts.setTreatmentNotStartedAlertDate(today());

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

    private Date today() {
        return new Date(DateTimeZone.UTC.convertUTCToLocal(new LocalDate().toDate().getTime()));
    }

}
