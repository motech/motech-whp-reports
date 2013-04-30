CREATE INDEX patient_id_idx ON whp_reports.adherence_record (patient_id);
CREATE INDEX pill_monday_idx ON whp_reports.adherence_record (date_trunc('week', pill_date::timestamp));
CREATE INDEX attempt_monday_idx ON whp_reports.call_log (date_trunc('week', attempt_date_time::timestamp));
CREATE INDEX ad_code_idx ON whp_reports.sputum_tracking (alternate_diagnosis_code);
CREATE INDEX rc_code_idx ON whp_reports.sputum_tracking (reason_for_closure);
CREATE INDEX therapy_fk_idx ON whp_reports.patient_treatment (therapy_fk);
CREATE INDEX patient_fk_idx ON whp_reports.patient_therapy(patient_fk);
