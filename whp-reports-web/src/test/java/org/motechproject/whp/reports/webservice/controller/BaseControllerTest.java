package org.motechproject.whp.reports.webservice.controller;


import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class BaseControllerTest {

    @Test
    public void shouldReturnErrorStatusCodeOnException() throws Exception {
        BaseController controller = new BaseController() {
            @RequestMapping(value = "test", method = RequestMethod.GET)
            public String throwException() {
                throw new RuntimeException("Expected exception");
            }
        };

        standaloneSetup(controller).build()
                .perform(get("/test"))
                .andExpect(status().isInternalServerError());
    }
}
