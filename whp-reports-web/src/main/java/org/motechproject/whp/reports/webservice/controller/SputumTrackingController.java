package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sputumTracking")
public class SputumTrackingController  extends BaseController{

    private ContainerRecordService containerRecordService;

    @Autowired
    public SputumTrackingController(ContainerRecordService containerRecordService) {
        this.containerRecordService = containerRecordService;
    }

    @RequestMapping(value = "/containerRegistrationMeasure")
    @ResponseBody
    public void registerContainer(@RequestBody ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        containerRecordService.recordContainerRegistration(containerRegistrationReportingRequest);
    }

    @RequestMapping(value = "/sputumLabResultsMeasure")
    @ResponseBody
    public void captureLabResults(@RequestBody SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest) {
        containerRecordService.recordLabResults(sputumLabResultsCaptureReportingRequest);
    }

    @RequestMapping(value = "/containerStatusMeasure")
    @ResponseBody
    public void updateContainerStatus(@RequestBody ContainerStatusReportingRequest containerStatusReportingRequest) {
        containerRecordService.updateContainerStatus(containerStatusReportingRequest);
    }

    @RequestMapping(value = "/containerPatientMappingMeasure")
    @ResponseBody
    public void updateContainerPatientMapping(@RequestBody ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest) {
        containerRecordService.updateContainerPatientMapping(containerPatientMappingReportingRequest);
    }

    @RequestMapping(value = "/updateUserGivenPatientDetails", method = RequestMethod.POST)
    @ResponseBody
    public void updateUserGivenPatientDetails(@RequestBody UserGivenPatientDetailsReportingRequest userGivenPatientDetailsReportingRequest) {
        containerRecordService.updateContainerUserGivenDetails(userGivenPatientDetailsReportingRequest);
    }

    @RequestMapping(value = "/deleteUserGivenPatientDetails", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUserGivenPatientDetails(@RequestBody UserGivenPatientDetailsReportingRequest userGivenPatientDetailsReportingRequest) {
        containerRecordService.deleteContainerUserGivenDetails(userGivenPatientDetailsReportingRequest);
    }
}
