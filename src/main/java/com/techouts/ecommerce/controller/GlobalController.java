package com.techouts.ecommerce.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String pageNotFound() {
        return "page404";
    }
}
