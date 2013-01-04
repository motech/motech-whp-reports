package org.motechproject.whp.reports.webservice.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        logger.error("Error occurred", ex);
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public List<FieldError> handleError(MethodArgumentNotValidException e, HttpServletResponse response) {
        logger.error("Bad Request \n" + e.getBindingResult().toString());
        return e.getBindingResult().getFieldErrors();
    }
}