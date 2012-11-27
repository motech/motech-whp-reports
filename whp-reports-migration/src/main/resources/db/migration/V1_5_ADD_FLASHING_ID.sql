ALTER TABLE whp_reports.flashing_log add column flashing_call_id  varchar(32);
UPDATE whp_reports.flashing_log set flashing_call_id='0' WHERE flashing_call_id is null;
ALTER TABLE whp_reports.flashing_log ALTER COLUMN flashing_call_id set NOT NULL;

ALTER TABLE whp_reports.adherence_call_log add column flashing_call_id  varchar(32);
UPDATE whp_reports.adherence_call_log set flashing_call_id='0' WHERE flashing_call_id is null;
ALTER TABLE whp_reports.adherence_call_log ALTER COLUMN flashing_call_id set NOT NULL;