CREATE TABLE whp_reports.patient_reminder_call_log (
    call_log_id serial NOT NULL,
    call_id varchar(50) UNIQUE,
    request_id varchar(255),
    patient_id varchar(20),
    start_date_time timestamp,
    call_answered varchar(20),
    end_date_time timestamp,
    attempt_date_time timestamp,
    attempt numeric,
    disconnection_type varchar(30),
    reminder_type varchar(100),
    mobile_number varchar(15),
    PRIMARY KEY( call_log_id )
);