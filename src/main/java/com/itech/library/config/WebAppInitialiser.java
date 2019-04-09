package com.itech.library.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer  {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;//new Class[]{WebConfig.class};
        //return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
