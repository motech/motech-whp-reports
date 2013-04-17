package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String homePage() throws IOException {
        return "home";
    }

    @RequestMapping(value="/patientReportsFilter", method = RequestMethod.GET)
    public String patientReportsPage(Model uiModel) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "reports/patientReportsFilter";
    }

    @RequestMapping(value="/containerReports", method = RequestMethod.GET)
    public String containerReportsPage(Model uiModel) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "reports/containerTracking";
    }

    @RequestMapping(value="/dashboard/{visualizationName}", method = RequestMethod.GET)
    public String allVisualizations(Model uiModel, @PathVariable String visualizationName) throws IOException {
        uiModel.addAttribute("districts", districtService.getAllDistricts());
        return "dashboard/" + visualizationName;
    }
}



