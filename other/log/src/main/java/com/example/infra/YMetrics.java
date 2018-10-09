package com.example.infra;

import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class YMetrics {
    private final static Logger LOGGER = LoggerFactory.getLogger(YMetrics.class);
    public static MetricRegistry METRIC_REGISTRY = new MetricRegistry();

    public static class HttpRequest {
        public static void log(ServletRequest servletRequest, ServletResponse servletResponse, long cost) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

            String url = httpServletRequest.getRequestURL().toString();
            String method = httpServletRequest.getMethod();
            String statusCode = formatStatusCode(httpServletResponse.getStatus());

            String metricName = getHttpRequestMetricsName(method, url, statusCode);
            METRIC_REGISTRY.timer(metricName).update(cost, TimeUnit.MILLISECONDS);
            LOGGER.debug("response time: ({}) {} ({} ms)", method, url, cost);
        }

        private static String getHttpRequestMetricsName(String method, String url, String statusCode) {
            try {
                String path = formatPath((new URL(url)).getPath());
                return MetricRegistry.name(path, method, statusCode);
            } catch (MalformedURLException ignored) {
            }

            return MetricRegistry.name("others", method, statusCode);
        }

        private static String formatPath(String path) {
            List<String> pathComponents = Arrays.asList(path.split("/"));
            int toIndex = pathComponents.size() < 5 ? pathComponents.size() : 5;
            return String.join("/", pathComponents.subList(0, toIndex));
        }

        private static String formatStatusCode(long statusCode) {
            return String.format("%sxx", (int)statusCode/100);
        }
    }

    public static class AsyncHttpClient {
        public static void log(long numberOfRequest) {
            METRIC_REGISTRY
                    .meter(MetricRegistry.name(org.asynchttpclient.AsyncHttpClient.class))
                    .mark(numberOfRequest);
        }
    }
}
