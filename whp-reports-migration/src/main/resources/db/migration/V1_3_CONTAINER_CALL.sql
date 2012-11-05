CREATE TABLE whp_reports.container_call_log (
    call_log_id serial NOT NULL,
    call_id varchar(32) UNIQUE,
    provider_id varchar(20),
    start_date_time timestamp,
    end_date_time timestamp,
    duration numeric,
    disconnection_type varchar(30),
    mobile_number varchar(15)
    );




