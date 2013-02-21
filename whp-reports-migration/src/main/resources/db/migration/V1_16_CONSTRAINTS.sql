CREATE INDEX provider_district_idx ON whp_reports.patient_treatment (provider_district);
CREATE INDEX patient_treatment_start_date_idx ON whp_reports.patient_treatment (start_date);
CREATE INDEX patient_treatment_end_date_idx ON whp_reports.patient_treatment (end_date);

ALTER TABLE whp_reports.patient_therapy
ADD CONSTRAINT patient_therapy_fk
FOREIGN KEY (patient_fk) REFERENCES whp_reports.patient(patient_pk);

ALTER TABLE whp_reports.patient_treatment
ADD CONSTRAINT treatment_therapy_fk
FOREIGN KEY (therapy_fk) REFERENCES whp_reports.patient_therapy(therapy_pk);

ALTER TABLE whp_reports.patient
ADD CONSTRAINT patient_address_fk
FOREIGN KEY (patient_address_fk) REFERENCES whp_reports.patient_address(address_pk);


