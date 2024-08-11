package com.example.realschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e) {
        String errorMsg;
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
