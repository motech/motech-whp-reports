package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.service.ContainerRegistrationCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/containerRegistrationCallLog")
public class ContainerRegistrationCallLogController extends BaseController {

    private ContainerRegistrationCallLogService containerRegistrationCallLogService;

    @Autowired
    public ContainerRegistrationCallLogController(ContainerRegistrationCallLogService containerRegistrationCallLogService) {
        this.containerRegistrationCallLogService = containerRegistrationCallLogService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void create(@RequestBody ContainerRegistrationCallLogRequest request) {
        containerRegistrationCallLogService.save(new DomainBuilder().buildContainerRegistrationCallLog(request));
    }
}
