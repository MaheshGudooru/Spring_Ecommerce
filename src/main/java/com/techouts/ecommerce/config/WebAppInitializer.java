package com.techouts.ecommerce.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class, HibernateConfig.class}; // root application context
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class}; // DispatcherServlet application context
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // maps DispatcherServlet to "/"
    }
}
