CREATE TABLE whp_reports.call_log (
    call_log_id serial NOT NULL,
    provider_id varchar(20),
    start_time numeric,
    end_time numeric,
    called_by varchar(20),
    call_status varchar(10),
    adherence_status varchar(10)
);

CREATE TABLE whp_reports.provider (
    id serial NOT NULL,
    provider_id varchar(20),
    primary_mobile varchar(15),
    secondary_mobile varchar(15),
    tertiary_mobile varchar(15),
    district varchar(20)
);

CREATE TABLE whp_reports.patient_adherence_submission (
    id serial NOT NULL,
    time_taken numeric,
    channel_id varchar(10),
    status varchar(10),
    patient_id varchar(20),
    submitted_value numeric,
    provider_id varchar(20),
    submitted_by varchar(10),
    valid varchar(10),
    call_id varchar(20)
);

CREATE TABLE whp_reports.date_dimension (
    id serial NOT NULL,
    dt_year numeric,
    dt_month numeric,
    dt_week numeric,
    dt_day numeric
);
