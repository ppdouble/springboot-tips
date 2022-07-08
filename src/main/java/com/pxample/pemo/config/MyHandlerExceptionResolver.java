package com.pxample.pemo.config;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Configuration
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, @Nullable Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof ArithmeticException) {
            modelAndView.setViewName("exceptionsresolver");
        }
        if (e instanceof NullPointerException) {
            modelAndView.setViewName("exceptionsresolver");
        }
        modelAndView.addObject("exception", e.toString() + " from HandlerExceptionResolver");
        return modelAndView;
    }
}