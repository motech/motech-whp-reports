package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.service.AdherenceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdherenceRecordController {

    private AdherenceRecordService adherenceRecordService;

    @Autowired
    public AdherenceRecordController(AdherenceRecordService adherenceRecordService) {
        this.adherenceRecordService = adherenceRecordService;
    }

    @RequestMapping("/adherence/record")
    @ResponseBody
    public void adherenceRecord(@RequestBody AdherenceRecordDTO adherenceRecordDTO){
        adherenceRecordService.save(adherenceRecordDTO);
    }
}
