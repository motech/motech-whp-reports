package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.ContainerRegistrationRequest;
import org.motechproject.whp.reports.service.SputumTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/sputumTracking")
public class SputumTrackingController {

    private SputumTrackingService sputumTrackingService;

    @Autowired
    public SputumTrackingController(SputumTrackingService sputumTrackingService) {
        this.sputumTrackingService= sputumTrackingService;
    }

    @RequestMapping(value = "/containerRegistrationMeasure")
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@RequestBody ContainerRegistrationRequest containerRegistrationRequest) {
        sputumTrackingService.save(DomainBuilder.buildSputumTrackingContainerRegistrationLog(containerRegistrationRequest));
    }
}
