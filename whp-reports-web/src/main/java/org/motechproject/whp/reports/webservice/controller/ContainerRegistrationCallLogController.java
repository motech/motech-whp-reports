package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.builder.DomainMapper;
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
    private DomainMapper domainMapper;

    @Autowired
    public ContainerRegistrationCallLogController(ContainerRegistrationCallLogService containerRegistrationCallLogService, DomainMapper domainMapper) {
        this.containerRegistrationCallLogService = containerRegistrationCallLogService;
        this.domainMapper = domainMapper;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public void create(@RequestBody ContainerRegistrationCallLogRequest request) {
        containerRegistrationCallLogService.save(domainMapper.mapContainerRegistrationCallLog(request));
    }
}
