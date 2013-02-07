package org.motechproject.whp.reports.webservice.controller;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public abstract class ControllerTest {

    protected String getJSON(Object object) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writer().writeValueAsString(object);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
