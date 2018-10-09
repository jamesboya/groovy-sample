package com.example;

import com.example.dao.RequestLoggerFactory;
import com.example.dao.SampleClient;
import com.example.infra.YAsyncHttpClient;
import com.example.services.SampleService;
import org.asynchttpclient.AsyncHttpClient;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

public class SampleServiceTest {
    private ServiceLocator serviceLocator;
    private SampleService sampleService;

    @Before
    public void setup() {
        serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        AsyncHttpClient asyncHttpClient = getAsyncHttpClient();

        ServiceLocatorUtilities.bind(serviceLocator, new AbstractBinder() {
            @Override
            protected void configure() {
                bind(asyncHttpClient).to(AsyncHttpClient.class);
                bind(RequestLoggerFactory.class).to(RequestLoggerFactory.class).in(PerLookup.class);
                bind(YAsyncHttpClient.class).to(YAsyncHttpClient.class).in(PerLookup.class);

                bind(SampleService.class).to(SampleService.class);
                bind(SampleClient.class).to(SampleClient.class);
            }
        });

        sampleService = serviceLocator.getService(SampleService.class);
    }

    @Test
    public void fetchAllService() {
        sampleService.fetchAllService();
    }

    @After
    public void teardown() {
        serviceLocator.shutdown();
    }

    private AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient(config()
                .setUseInsecureTrustManager(true)
                .setKeepAlive(false)
                .setRequestTimeout(5000)
                .setConnectionTtl(500)
                .setConnectTimeout(1000));
    }
}
