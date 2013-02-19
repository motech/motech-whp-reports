CREATE TABLE whp_reports.adherence_audit_log (
    audit_log_id bigserial NOT NULL,
    patient_id varchar(32),
    provider_id varchar(30),
    tb_id varchar(30),
    creation_time timestamp,
    dose_date date,
    user_id varchar(30),
    doses_taken integer,
    pill_status varchar(20),
    channel varchar(10)
);






