package org.motechproject.whp.reports.builder;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.motechproject.whp.reports.contract.patient.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PatientRequestBuilder {
    private PatientDTO patientDTO = new PatientDTO();
    private AddressDTO patientAddress;
    private PatientAlertsDTO patientAlerts;
    private TherapyDTO therapy;

    public PatientRequestBuilder withDefaults(){
        defaultPatient();
        patientAddress = defaultPatientAddress();
        patientAlerts = defaultPatientAlerts();
        therapy = defaultTherapy();

        patientDTO.setTherapies(asList(therapy));
        patientDTO.setPatientAddress(patientAddress);
        patientDTO.setPatientAlerts(patientAlerts);
        return this;
    }

    private void defaultPatient() {
        patientDTO.setFirstName("firstName");
        patientDTO.setLastName("lastName");
        patientDTO.setGender("Male");
        patientDTO.setOnActiveTreatment("Y");
        patientDTO.setPatientId("patientid");
        patientDTO.setPatientStatus("status");
        patientDTO.setPhi("phi");
        patientDTO.setPhoneNumber("phone");
    }

    private TherapyDTO defaultTherapy() {
        TherapyDTO therapy = new TherapyDTO();
        therapy.setTherapyId("therapyId");
        therapy.setCloseDate(today(new LocalDate()));
        therapy.setStartDate(today(new LocalDate()));

        therapy.setTreatmentCategory("RNTCP Category 2");

        therapy.setCpStartDate(today(new LocalDate()));
        therapy.setCpEndDate(today(new LocalDate()));
        therapy.setCpPillsRemaining(4);
        therapy.setCpPillsTaken(10);
        therapy.setCpTotalDoses(20);

        therapy.setIpStartDate(today(new LocalDate()));
        therapy.setIpEndDate(today(new LocalDate()));
        therapy.setIpPillsRemaining(4);
        therapy.setIpPillsTaken(10);
        therapy.setIpTotalDoses(20);

        therapy.setEipStartDate(today(new LocalDate()));
        therapy.setEipEndDate(today(new LocalDate()));
        therapy.setEipPillsRemaining(4);
        therapy.setEipPillsTaken(10);
        therapy.setEipTotalDoses(20);

        therapy.setCreationDate(today(new LocalDate()));
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

    private List<TreatmentDTO> defaultTreatments() {
        List<TreatmentDTO> treatments  = new ArrayList<>();
        treatments.add(defaultTreatment());
        return treatments;
    }

    private TreatmentDTO defaultTreatment() {
        TreatmentDTO treatment = new TreatmentDTO();
        treatment.setCurrentTreatment("Y");
        treatment.setEndDate(today(new LocalDate().minusDays(2)));
        treatment.setStartDate(today(new LocalDate().minusDays(4)));
        treatment.setIsPaused("Y");
        treatment.setPatientType("type");
        treatment.setPausedDate(today(new LocalDate()));
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

    private Date today(LocalDate date) {
        return new Date(DateTimeZone.UTC.convertUTCToLocal(date.toDate().getTime()));
    }

    private PatientAlertsDTO defaultPatientAlerts() {
        PatientAlertsDTO patientAlerts = new PatientAlertsDTO();
        patientAlerts.setAdherenceMissingWeeks(1);
        patientAlerts.setAdherenceMissingWeeksAlertSeverity(1);
        patientAlerts.setAdherenceMissingWeeksAlertDate(today(new LocalDate()));
        patientAlerts.setCumulativeMissedDoses(1);
        patientAlerts.setCumulativeMissedDosesAlertSeverity(1);
        patientAlerts.setCumulativeMissedDosesAlertDate(today(new LocalDate()));
        patientAlerts.setTreatmentNotStarted(1);
        patientAlerts.setTreatmentNotStartedAlertSeverity(1);
        patientAlerts.setTreatmentNotStartedAlertDate(today(new LocalDate()));

        return patientAlerts;
    }

    private AddressDTO defaultPatientAddress() {
        AddressDTO patientAddress = new AddressDTO();
        patientAddress.setBlock("block");
        patientAddress.setDistrict("district");
        patientAddress.setLandmark("landmark");
        patientAddress.setVillage("village");
        patientAddress.setState("state");
        patientAddress.setLocation("location");

        return patientAddress;
    }

    public PatientDTO build(){
        return patientDTO;
    }
}