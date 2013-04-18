package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.service.AdherenceAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adherence")
public class AdherenceAuditLogController extends BaseController{


    private AdherenceAuditLogService adherenceAuditLogService;

    @Autowired
    public AdherenceAuditLogController(AdherenceAuditLogService adherenceAuditLogService) {
        this.adherenceAuditLogService = adherenceAuditLogService;
    }

    @RequestMapping(value = "/auditlog")
    @ResponseBody
    public void updateProvider(@RequestBody AdherenceAuditLogDTO adherenceAuditLogDTO){
        adherenceAuditLogService.save(adherenceAuditLogDTO);
    }
}
