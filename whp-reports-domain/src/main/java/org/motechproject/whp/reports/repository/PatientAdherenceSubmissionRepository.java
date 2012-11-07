package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAdherenceSubmissionRepository extends JpaRepository<PatientAdherenceSubmission, Long>{
}
