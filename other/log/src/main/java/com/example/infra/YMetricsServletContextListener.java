package com.example.infra;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.annotation.WebListener;

@WebListener
public class YMetricsServletContextListener extends MetricsServlet.ContextListener {
    @Override
    protected MetricRegistry getMetricRegistry() {
        return YMetrics.METRIC_REGISTRY;
    }
}
