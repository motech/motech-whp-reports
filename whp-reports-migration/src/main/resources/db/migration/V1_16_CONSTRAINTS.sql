CREATE UNIQUE INDEX provider_id_idx ON whp_reports.provider (provider_id);
CREATE INDEX treatment_district_idx ON whp_reports.patient_treatment (provider_district);
CREATE INDEX patient_treatment_start_date_idx ON whp_reports.patient_treatment (start_date);
CREATE INDEX patient_treatment_end_date_idx ON whp_reports.patient_treatment (end_date);
CREATE INDEX district_idx ON whp_reports.adherence_record (district);
CREATE INDEX pill_date_idx ON whp_reports.adherence_record (pill_date);
CREATE INDEX provider_district_idx ON whp_reports.provider (district);
CREATE INDEX creation_time_idx ON whp_reports.adherence_audit_log (creation_time);
CREATE INDEX attempt_date_time_idx ON whp_reports.provider_reminder_call_log (attempt_date_time);
CREATE INDEX sputum_district_idx ON whp_reports.sputum_tracking (provider_district);

ALTER TABLE whp_reports.patient_therapy
ADD CONSTRAINT patient_therapy_fk
FOREIGN KEY (patient_fk) REFERENCES whp_reports.patient(patient_pk);

ALTER TABLE whp_reports.patient_treatment
ADD CONSTRAINT treatment_therapy_fk
FOREIGN KEY (therapy_fk) REFERENCES whp_reports.patient_therapy(therapy_pk);

ALTER TABLE whp_reports.patient
ADD CONSTRAINT patient_address_fk
FOREIGN KEY (patient_address_fk) REFERENCES whp_reports.patient_address(address_pk);

ALTER TABLE whp_reports.provider_reminder_call_log
ADD CONSTRAINT provider_call_log_fk
FOREIGN KEY (provider_id) REFERENCES whp_reports.provider(provider_id);

ALTER TABLE whp_reports.sputum_tracking
ADD CONSTRAINT diagnosis_code_fk
FOREIGN KEY (alternate_diagnosis_code) REFERENCES whp_reports.alternate_diagnosis(code);

ALTER TABLE whp_reports.sputum_tracking
ADD CONSTRAINT reason_closure_fk
FOREIGN KEY (reason_for_closure) REFERENCES whp_reports.reasons_for_closure(code);