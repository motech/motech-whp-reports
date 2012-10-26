package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.service.SputumTrackingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sputumTracking")
public class SputumTrackingController {

    private SputumTrackingService sputumTrackingService;

    public SputumTrackingController(SputumTrackingService sputumTrackingService) {
        this.sputumTrackingService= sputumTrackingService;
    }
}
