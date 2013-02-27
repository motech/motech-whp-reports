update whp_reports.provider_reminder_call_log set adherence_reported = 'Y' where adherence_reported = 'true ' or adherence_reported = 'true';
update whp_reports.provider_reminder_call_log set adherence_reported = 'N' where adherence_reported = 'false' or adherence_reported is null;
ALTER TABLE whp_reports.provider_reminder_call_log alter column adherence_reported type char(1);
ALTER TABLE whp_reports.provider_reminder_call_log alter column adherence_reported SET DEFAULT 'N';
