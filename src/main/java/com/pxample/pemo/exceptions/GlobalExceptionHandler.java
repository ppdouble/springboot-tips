package com.pxample.pemo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //to write global code that can be applied to a wide range of controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionmsg", "ArithmeticException from GlobalExceptionHandler with ControllerAdvice");
        modelAndView.setViewName("exceptions");
        return modelAndView;
    }

    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public ModelAndView handleNullPointerException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionmsg", "NullPointerException from GlobalExceptionHandler with ControllerAdvice");
        modelAndView.setViewName("exceptions");
        return modelAndView;
    }
}
