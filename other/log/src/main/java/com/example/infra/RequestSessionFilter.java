package com.example.infra;

import com.example.dao.RequestLoggerFactory;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.util.UUID;

public class RequestSessionFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ContainerRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        createSessionId();

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long cost = System.currentTimeMillis() - startTime;

        YMetrics.HttpRequest.log(servletRequest, servletResponse, cost);
    }

    private void createSessionId() {
        String id = UUID.randomUUID().toString();
        ThreadContext.put(RequestLoggerFactory.SESSIONID, id);
        LOGGER.trace("initialize session: {}", id);
    }

    @Override
    public void destroy() {

    }
}
