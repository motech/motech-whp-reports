package org.motechproject.whp.reports.webservice.controller;


import org.motechproject.whp.reports.webservice.request.AdherenceCaptureRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adherence")
public class AdherenceCaptureController {

    @RequestMapping(method = RequestMethod.POST, value = "capture")
    public @ResponseBody String update(@RequestBody AdherenceCaptureRequest adherenceCaptureRequest) {
        return null;
    }


}
