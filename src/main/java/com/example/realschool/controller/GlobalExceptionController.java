package com.example.realschool.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e) {
        String errorMsg = null;
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        if (e.getMessage() != null) {
            errorMsg = e.getMessage();
        } else if (e.getCause() != null) {
            errorMsg = e.getCause().toString();
        } else {
            errorMsg = e.toString();
        }
        errorPage.addObject("errormsg", errorMsg);
        return errorPage;
    }
}
