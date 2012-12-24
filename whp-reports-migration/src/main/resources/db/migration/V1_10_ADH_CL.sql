ALTER TABLE whp_reports.adherence_call_log add column attempt_time timestamp;
ALTER TABLE whp_reports.adherence_call_log add column call_answered varchar(30);
ALTER TABLE whp_reports.adherence_call_log add column disconnection_type varchar(30);
