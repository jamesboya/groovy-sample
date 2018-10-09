package com.example.infra;

import com.example.dao.RequestLoggerFactory;
import org.asynchttpclient.*;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class YAsyncHttpClient {
    private final AsyncHttpClient asyncHttpClient;
    private final RequestLoggerFactory requestLoggerFactory;

    @Inject
    public YAsyncHttpClient(AsyncHttpClient asyncHttpClient, RequestLoggerFactory requestLoggerFactory) {
        this.asyncHttpClient = asyncHttpClient;
        this.requestLoggerFactory = requestLoggerFactory;
    }

    public CompletableFuture<Response> executeRequest(Request request) {
        String url = request.getUrl();
        return applyLogging(asyncHttpClient.executeRequest(request).toCompletableFuture(), url);
    }

    public CompletableFuture<Response> executeRequest(RequestBuilder requestBuilder) {
        return executeRequest(requestBuilder.build());
    }

    private CompletableFuture<Response> applyLogging(CompletableFuture<Response> response, String url) {
        return response.thenApply(requestLoggerFactory.getLogger(url)::log);
    }
}
