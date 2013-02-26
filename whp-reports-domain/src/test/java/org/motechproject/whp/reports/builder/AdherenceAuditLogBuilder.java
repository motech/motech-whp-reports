package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;

import static org.motechproject.whp.reports.date.WHPDateTime.toSqlDate;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlTimestamp;

public class AdherenceAuditLogBuilder {

    private AdherenceAuditLog adherenceAuditLog = new AdherenceAuditLog();

    public AdherenceAuditLogBuilder withDefaults(){

        DateTime today = DateTime.now();
        adherenceAuditLog.setCreationTime(toSqlTimestamp(today));
        adherenceAuditLog.setPatientId("patientId");
        adherenceAuditLog.setProviderId("providerId");
        adherenceAuditLog.setUserId("user");
        adherenceAuditLog.setDoseDate(toSqlDate(today));
        adherenceAuditLog.setNumberOfDosesTaken(2);
        adherenceAuditLog.setPillStatus("TAKEN");
        adherenceAuditLog.setChannel("channel");
        adherenceAuditLog.setTbId("tbId");
        return this;

    }

    public AdherenceAuditLog build(){
        return adherenceAuditLog;
    }
}
