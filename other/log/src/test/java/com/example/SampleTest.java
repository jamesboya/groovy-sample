package com.example;

import org.asynchttpclient.Response;
import org.asynchttpclient.channel.NoopChannelPool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

public class SampleTest {
    private static Logger LOGGER = LoggerFactory.getLogger(SampleTest.class);

    @Test
    public void asyncHttpTest() {
        CompletableFuture<Response> cf =
                asyncHttpClient(config().setUseInsecureTrustManager(true).setChannelPool(NoopChannelPool.INSTANCE).build())
                        .prepareGet("https://sapi0.shp.tw1.yahoo.com:4443/api/promotionService/v1/marketingEvent/act2-z2-b-170817-OutletSale")
                        .execute()
                        .toCompletableFuture();

        try {
            Response response = cf.get();

            System.out.println(response.getResponseBody());
            assertThat(response.getResponseBody()).isNotEmpty();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void streamTest() {
        int[] result = Arrays.stream(new int[]{10, 87, 97, 43, 121, 20})
                .sorted()
                .toArray();
    }

    @Test
    public void exceptionTest() {
        try {
            new FooException().foo();
        } catch (RuntimeException e) {
            LOGGER.error("log: {}", "123");
            LOGGER.error("exception occurred", new IllegalArgumentException(e));
        }
    }

    @Test
    public void stringTest() {
        assertThat(String.join(",", Arrays.asList("a", "b", "c"))).isEqualTo("a,b,c");
    }

    @Test
    public void enumTest() {
        assertThat(FooEnum.valueOf("FOO")).isEqualTo(FooEnum.FOO);
        assertThat(FooEnum.FOO.toString()).isEqualTo("foo");
    }
}


