package com.example.dao;

import org.apache.logging.log4j.ThreadContext;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLoggerFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(RequestLoggerFactory.class);
    public static final String SESSIONID = "sessionid";

    private final String threadId;

    public RequestLoggerFactory() {
        this.threadId = ThreadContext.get(SESSIONID);
        LOGGER.debug("create logger factory for {}", threadId);
    }

    public RequestLogger getLogger(String url) {
        return new RequestLogger(threadId, url);
    }

}

