package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    DistrictService districtService;

    @Autowired
    public HomeController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String homePage = "/home";

        response.sendRedirect(request.getContextPath() + response.encodeURL(homePage));
    }

    @RequestMapping(value="/patientReportsFilter", method = RequestMethod.GET)
    public String patientReportsPage(Model uiModel) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "reports/patientReportsFilter";
    }

    @RequestMapping(value="/containerReports", method = RequestMethod.GET)
    public String containerReportsPage(Model uiModel) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "dashboard/containerTracking";
    }

    @RequestMapping(value="/dashboard/tbRegistration", method = RequestMethod.GET)
    public String tbRegistrationPage(Model uiModel) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "dashboard/tbRegistration";
    }
}



