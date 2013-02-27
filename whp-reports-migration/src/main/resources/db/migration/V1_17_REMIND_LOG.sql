update whp_reports.provider_reminder_call_log set adherence_reported = 'Y' where adherence_reported = 'true ' or adherence_reported = 'true';
update whp_reports.provider_reminder_call_log set adherence_reported = 'N' where adherence_reported = 'false' or adherence_reported is null;
ALTER TABLE whp_reports.provider_reminder_call_log add column adherence_reported char(1) default 'N';