package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.validation.validator.BeanValidator;
import org.motechproject.whp.reports.builder.DomainMapper;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.service.ContainerRegistrationCallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/containerRegistrationCallLog")
public class ContainerRegistrationCallLogController extends BaseController {

    private ContainerRegistrationCallLogService containerRegistrationCallLogService;
    private DomainMapper domainMapper;
    private BeanValidator beanValidator;

    @Autowired
    public ContainerRegistrationCallLogController(ContainerRegistrationCallLogService containerRegistrationCallLogService, DomainMapper domainMapper, BeanValidator beanValidator) {
        this.containerRegistrationCallLogService = containerRegistrationCallLogService;
        this.domainMapper = domainMapper;
        this.beanValidator = beanValidator;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public List<ObjectError> create(@RequestBody ContainerRegistrationCallLogRequest request, HttpServletResponse response) {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(request, request.getClass().getSimpleName());
        beanValidator.validate(request, "", result);

        if(result.hasErrors()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(String.format("Could not create containerRegistrationCallLog due to validation errors: %s", result.getAllErrors()));
            return result.getAllErrors();
        }

        containerRegistrationCallLogService.save(domainMapper.mapContainerRegistrationCallLog(request));
        return null;
    }
}
