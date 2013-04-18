package org.motechproject.whp.reports.webservice.controller;

import org.motechproject.whp.reports.contract.provider.ProviderDTO;
import org.motechproject.whp.reports.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProviderController extends BaseController {

    private ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }


    @RequestMapping(value = "/provider/update")
    @ResponseBody
    public void updateProvider(@RequestBody ProviderDTO providerDTO){
        providerService.save(providerDTO);
    }
}
