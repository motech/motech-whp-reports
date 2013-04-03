package org.motechproject.whp.reports.contract.builder;

import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.UserGivenPatientDetailsRequest;

import java.util.Date;

public class ContainerRegistrationRequestBuilder {

    public static ContainerRegistrationReportingRequest defaultRequest(){
        ContainerRegistrationReportingRequest request = new ContainerRegistrationReportingRequest();
        request.setProviderDistrict("district");
        request.setCallId("callId");
        request.setChannelId("channelId");
        request.setContainerId("containerId");
        request.setDiagnosis("diagnosis");
        request.setInstance("instance");
        request.setIssuedOn(new Date());
        request.setProviderId("provider");
        request.setStatus("Open");
        request.setSubmitterId("submitterId");
        request.setSubmitterRole("role");
        request.setUserGivenPatientDetailsRequest(defaultUserGivenDetails());
        return request;
    }

    private static UserGivenPatientDetailsRequest defaultUserGivenDetails() {
        UserGivenPatientDetailsRequest details = new UserGivenPatientDetailsRequest();
        details.setPatientName("givenPatientName");
        details.setPatientId("givenPatientID");
        details.setPatientAge(21);
        details.setGender("gender");
        return details;
    }
}
