package com.techouts.ecommerce.config;

import java.util.TimeZone;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.techouts.ecommerce")
public class AppConfig {

//    static {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
//    }
}
