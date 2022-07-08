package com.pxample.pemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

//@Configuration
public class MySimpleMappingExceptionResolver {

    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties propertiesMap = new Properties();
        // Exception name and View name
        propertiesMap.put("java.lang.ArithmeticException", "exceptionsresolver");
        propertiesMap.put("java.lang.NullPointerException", "exceptionsresolver");  // Is it a bug for multi-exceptions program with only one view? The view name can be various for different exceptions.
        simpleMappingExceptionResolver.setExceptionMappings(propertiesMap);

        // default attribute name is "exception"
        return simpleMappingExceptionResolver;
    }


}

