package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.repository.AdherenceAuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdherenceAuditService extends BaseService<AdherenceAuditLogRepository, AdherenceAuditLog>{

    @Autowired
    public AdherenceAuditService(AdherenceAuditLogRepository repository) {
        super(repository);
    }

    
}
