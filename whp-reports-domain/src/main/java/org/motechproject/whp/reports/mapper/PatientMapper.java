package org.motechproject.whp.reports.mapper;

import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.contract.patient.TherapyDTO;
import org.motechproject.whp.reports.contract.patient.TreatmentDTO;
import org.motechproject.whp.reports.domain.patient.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientMapper {

    public void map(PatientDTO patientDTO, Patient patient) {
        mapPatientAttributes(patientDTO, patient);
        mapTherapies(patientDTO, patient);
        mapPatientAddress(patientDTO, patient);
        mapPatientAlerts(patientDTO, patient);
    }

    private void mapPatientAttributes(PatientDTO patientDTO, Patient patient) {
        BeanUtils.copyProperties(patientDTO, patient, new String[]{"patientAddress", "patientAlerts", "therapies"});
    }

    private void mapPatientAlerts(PatientDTO patientDTO, Patient patient) {
        BeanUtils.copyProperties(patientDTO.getPatientAlerts(), patient.getPatientAlerts());
    }

    private void mapPatientAddress(PatientDTO patientDTO, Patient patient) {
        BeanUtils.copyProperties(patientDTO.getPatientAddress(), patient.getPatientAddress());
    }

    private void mapTherapies(PatientDTO patientDTO, Patient patient) {
        patient.clearTherapies();

        List<Therapy> therapyList = new ArrayList<>();
        for(TherapyDTO therapyDTO : patientDTO.getTherapies()){
            Therapy therapy = new Therapy();
            BeanUtils.copyProperties(therapyDTO, therapy, new String[]{"treatments"});
            therapyList.add(therapy);

            therapy.getTreatments().clear();
            therapy.getTreatments().addAll(mapTreatments(therapyDTO.getTreatments()));
        }

        patient.getTherapies().addAll(therapyList);
    }

    private List<Treatment> mapTreatments(List<TreatmentDTO> treatmentDTOs) {
        List<Treatment> treatments =  new ArrayList<>();
        for(TreatmentDTO treatmentDTO : treatmentDTOs){
            Treatment treatment = new Treatment();
            BeanUtils.copyProperties(treatmentDTO, treatment);
            treatments.add(treatment);
        }
        return treatments;
    }
}
