ALTER TABLE whp_reports.sputum_tracking add column given_patient_id varchar(100);
ALTER TABLE whp_reports.sputum_tracking add column given_patient_name varchar(100);
ALTER TABLE whp_reports.sputum_tracking add column given_patient_age numeric;
ALTER TABLE whp_reports.sputum_tracking add column given_gender varchar(30);