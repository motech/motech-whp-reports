package org.motechproject.whp.reports.domain.patient;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Date;

@Data
@Embeddable
public class TreatmentDetails {

    @Column(name = "district_with_code")
    private String districtWithCode;

    @Column(name = "tb_unit_with_code")
    private String tbUnitWithCode;

    @Column(name = "ep_site")
    private String epSite;

    @Column(name = "other_investigations")
    private String otherInvestigations;

    @Column(name = "previous_treatment_history")
    private String previousTreatmentHistory;

    @Column(name = "hiv_status")
    private String hivStatus;

    @Column(name = "hiv_test_date")
    private Date hivTestDate;

    @Column(name = "members_below_six_years")
    private Integer membersBelowSixYears;

    @Column(name = "phc_referred")
    private String phcReferred;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "dot_centre")
    private String dotCentre;

    @Column(name = "provider_type")
    private String providerType;

    @Column(name = "cmf_doctor")
    private String cmfDoctor;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "contact_person_phone_number")
    private String contactPersonPhoneNumber;

    @Column(name = "xpert_test_result")
    private String xpertTestResult;

    @Column(name = "xpert_device_number")
    private String xpertDeviceNumber;

    @Column(name = "xpert_test_date")
    private Date xpertTestDate;

    @Column(name = "rif_resistance_result")
    private String rifResistanceResult;
}
