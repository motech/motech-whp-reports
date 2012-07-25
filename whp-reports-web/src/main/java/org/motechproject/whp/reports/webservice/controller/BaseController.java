package org.motechproject.whp.reports.webservice.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletResponse response) {
        logger.error("Error occurred", ex);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}