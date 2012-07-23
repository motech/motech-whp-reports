CREATE TABLE whp_reports.call_log (
    id numeric NOT NULL,
    provider_id varchar(20),
    start_time timestamp,
    end_time timestamp,
    called_by varchar(20)
);