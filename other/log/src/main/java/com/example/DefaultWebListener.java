package com.example;

import com.codahale.metrics.servlets.MetricsServlet;
import com.example.infra.RequestSessionFilter;
import com.example.parsec_generated.ParsecWebListener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

/**
 * Default Web listener, replace web.xml.
 */
@WebListener
public class DefaultWebListener extends ParsecWebListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Parsec default servlet registrations
        super.contextInitialized(sce);

        // Add additional servlet filter here
        ServletContext context = sce.getServletContext();

        // request session filter
        FilterRegistration.Dynamic requestSessionFilter = context.addFilter("requestSessionFilter", RequestSessionFilter.class);
        requestSessionFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        // metrics servlet
        ServletRegistration.Dynamic metricsSevlet = context.addServlet("metrics", MetricsServlet.class);
        metricsSevlet.addMapping("/metrics");
    }
}

