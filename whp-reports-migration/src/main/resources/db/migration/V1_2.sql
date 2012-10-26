CREATE TABLE whp_reports.sputum_tracking (
    sputum_tracking_id serial NOT NULL,
    container_id varchar(20),
    date_issued_on date,
    provider_id varchar(20),
    submitted_by varchar(10),
    submitter_id varchar(20),
    location_id varchar(20),
    instance varchar(20),
    channel_id varchar(3),
    patient_id varchar(20),
    container_status varchar(5),
    reason_for_closure varchar(60),
    alternate_diagnosis_code varchar(4)
);
