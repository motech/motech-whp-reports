ALTER TABLE whp_reports.flashing_log add column flashing_call_id  varchar(32) UNIQUE  NOT NULL;
ALTER TABLE whp_reports.adherence_call_log add column flashing_call_id  varchar(32) UNIQUE  NOT NULL;