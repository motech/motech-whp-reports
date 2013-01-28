ALTER TABLE whp_reports.adherence_call_log add column attempt_time timestamp;
ALTER TABLE whp_reports.adherence_call_log add column call_answered varchar(30);
ALTER TABLE whp_reports.adherence_call_log add column disconnection_type varchar(30);
ALTER TABLE whp_reports.adherence_call_log ALTER COLUMN flashing_call_id drop NOT NULL;
update whp_reports.adherence_call_log set attempt_time = start_date_time where attempt_time is null;