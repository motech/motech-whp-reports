package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "_patientAdherenceSubmissions")
public interface PatientAdherenceSubmissionRepository extends MotechJpaRepository<PatientAdherenceSubmission>{
}
