CREATE TABLE whp_reports.patient_reminder_call_log
(
  call_log_id serial NOT NULL,
  call_id character varying(32),
  request_id character varying(32),
  patient_id character varying(20),
  start_date_time timestamp without time zone,
  call_answered character varying(20),
  end_date_time timestamp without time zone,
  attempt_date_time timestamp without time zone,
  attempt numeric,
  disconnection_type character varying(30),
  reminder_type character varying(100),
  status character varying(30),
  mobile_number character varying(15),
  CONSTRAINT patient_reminder_call_log_pkey PRIMARY KEY (call_log_id),
  CONSTRAINT patient_reminder_call_log_call_id_key UNIQUE (call_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE whp_reports.patient_reminder_call_log
  OWNER TO postgres;