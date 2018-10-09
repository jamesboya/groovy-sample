package com.example;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;
import com.example.dao.RequestLoggerFactory;
import com.example.dao.SampleClient;
import com.example.infra.AsyncHttpClientShutdownListener;
import com.example.infra.YAsyncHttpClient;
import com.example.infra.YMetrics;
import com.example.parsec_generated.ParsecApplication;
import com.example.resources.SampleExceptionResource;
import com.example.services.SampleService;
import org.asynchttpclient.AsyncHttpClient;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

/**
 * This is the entry point of Jersey, which is defined in web.xml.
 */
@SuppressWarnings("unused")
public class DefaultApplication extends ParsecApplication {

    /**
     * Default constructor.
     */
    public DefaultApplication() {
        // Parsec default bindings and registers
        super();

        AsyncHttpClient asyncHttpClient = getAsyncHttpClient();
        register(new AsyncHttpClientShutdownListener(asyncHttpClient));

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(asyncHttpClient).to(AsyncHttpClient.class);
                bind(RequestLoggerFactory.class).to(RequestLoggerFactory.class).in(RequestScoped.class);
                bind(YAsyncHttpClient.class).to(YAsyncHttpClient.class).in(PerLookup.class);

                bind(SampleService.class).to(SampleService.class);
                bind(SampleClient.class).to(SampleClient.class);
            }
        });

        register(SampleExceptionResource.class);

        monitorOnConnectionPool();
        monitorOnAsyncHttpClient(asyncHttpClient);
    }

    private void monitorOnConnectionPool() {
        YMetrics.METRIC_REGISTRY.register(MetricRegistry.name(C3P0ConnectionPoolGauge.class),
                new C3P0ConnectionPoolGauge());
    }

    private void monitorOnAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
        ScheduledExecutorService asyncHttpClientMonitor = Executors.newSingleThreadScheduledExecutor();
        asyncHttpClientMonitor.scheduleAtFixedRate(() -> {
            YMetrics.AsyncHttpClient.log(asyncHttpClient.getClientStats().getTotalConnectionCount());
        }, 0, 1, TimeUnit.SECONDS);
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

class C3P0ConnectionPoolGauge extends RatioGauge {
    private final long maxConnections = 50;

    @Override
    protected Ratio getRatio() {
        int currentConnections = new Random().nextInt(50);
        return Ratio.of(currentConnections, maxConnections);
    }
}