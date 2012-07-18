CREATE TABLE whp_reports.call_duration_measure (
    id integer NOT NULL,
    flw_id bigint,
    call_id character varying(255),
    duration integer,
    type character varying(30),
    location_id bigint,
    start_time timestamp with time zone,
    end_time timestamp with time zone,
    called_number bigint,
    time_id bigint
);
