CREATE TABLE whp_reports.call_log (
    call_log_id serial NOT NULL,
    provider_id varchar(20),
    start_time timestamp,
    end_time timestamp,
    duration numeric,
    called_by varchar(20)
);