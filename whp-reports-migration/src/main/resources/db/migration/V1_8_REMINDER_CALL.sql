CREATE TABLE whp_reports.provider_reminder_call_log (
    call_log_id serial NOT NULL,
    call_id varchar(32) UNIQUE,
    request_id varchar(32),
    provider_id varchar(20),
    start_date_time timestamp,
    end_date_time timestamp,
    attempt_date_time timestamp,
    attempt numeric,
    disconnection_type varchar(30),
    reminder_type varchar(100),
    status varchar(30),
    mobile_number varchar(15)
);




