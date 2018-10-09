package com.example.dao;

import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogger {
    private static Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class);

    private final String threadId;
    private final String url;
    private final long startTime;

    public RequestLogger(String threadId, String url) {
        this.threadId = threadId;
        this.url = url;
        this.startTime = System.currentTimeMillis();
        LOGGER.debug("requesting {}", url);
    }

    public Response log(Response response) {
        LOGGER.debug("({}) complete request {} ({} ms)", threadId, url, System.currentTimeMillis() - startTime);
        LOGGER.trace("({}) response content {} {}", threadId, url, response.getResponseBody());
        return response;
    }

}
