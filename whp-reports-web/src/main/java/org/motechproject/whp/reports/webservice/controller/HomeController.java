package org.motechproject.whp.reports.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String homePage = "/home";

        response.sendRedirect(request.getContextPath() + response.encodeURL(homePage));
    }

    @RequestMapping(value="/patientReportsFilter")
    public String patientReportsPage(Model uiModel) throws IOException {
        uiModel.addAttribute("district", new ArrayList<String>());
        return "home/patientReportsFilter";
    }
}

