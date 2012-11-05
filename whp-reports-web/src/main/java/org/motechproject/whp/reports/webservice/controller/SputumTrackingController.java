package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/sputumTracking")
public class SputumTrackingController {

    private ContainerRecordService containerRecordService;

    @Autowired
    public SputumTrackingController(ContainerRecordService containerRecordService) {
        this.containerRecordService = containerRecordService;
    }

    @RequestMapping(value = "/containerRegistrationMeasure")
    @ResponseStatus(value = HttpStatus.OK)
    public void registerContainer(@RequestBody ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        containerRecordService.recordContainerRegistration(containerRegistrationReportingRequest);
    }

    @RequestMapping(value = "/sputumLabResultsMeasure")
    @ResponseStatus(value = HttpStatus.OK)
    public void captureLabResults(@RequestBody SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest) {
        containerRecordService.recordLabResults(sputumLabResultsCaptureReportingRequest);
    }

    @RequestMapping(value = "/containerStatusMeasure")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateContainerStatus(@RequestBody ContainerStatusReportingRequest containerStatusReportingRequest) {
        containerRecordService.updateContainerStatus(containerStatusReportingRequest);
    }

    @RequestMapping(value = "/containerPatientMappingMeasure")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateContainerPatientMapping(@RequestBody ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest) {
        containerRecordService.updateContainerPatientMapping(containerPatientMappingReportingRequest);
    }
}
