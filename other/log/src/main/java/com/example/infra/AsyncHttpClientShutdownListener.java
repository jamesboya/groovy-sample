package com.example.infra;

import org.asynchttpclient.AsyncHttpClient;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AsyncHttpClientShutdownListener implements ContainerLifecycleListener {
    private static Logger LOG = LoggerFactory.getLogger(AsyncHttpClientShutdownListener.class);

    private final AsyncHttpClient asyncHttpClient;

    public AsyncHttpClientShutdownListener(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }

    @Override
    public void onStartup(Container container) {
    }

    @Override
    public void onReload(Container container) {
    }

    @Override
    public void onShutdown(Container container) {
        LOG.debug("run the asyncHttpClient.close()");
        try {
            asyncHttpClient.close();
        } catch (IOException e) {
            LOG.error("problem occurred for asyncHttpClient.close(): ", e);
        }
    }
}
