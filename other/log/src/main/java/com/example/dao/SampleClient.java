package com.example.dao;

import com.example.infra.YAsyncHttpClient;
import org.asynchttpclient.Response;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

import static org.asynchttpclient.Dsl.get;

public class SampleClient {
    private static final String GET_PRODUCT_BUNDLE_URL = "https://b0.shp.tw1.yahoo.com:4443/v4/productBundles/7368595";
    private static final String GET_GD_URL = "http://ws0.shp.tw1.yahoo.com:4080/Webservice/GetGdData?gdid=7368595";
    private static final String GET_HTTPBIN_URL = "http://httpbin.org/get?foo=bar";

    private final YAsyncHttpClient client;

    @Inject
    public SampleClient(YAsyncHttpClient client) {
        this.client = client;
    }

    public CompletableFuture<Response> getProductBundle() {
        return client.executeRequest(get(GET_PRODUCT_BUNDLE_URL));
    }

    public CompletableFuture<Response> getWsGetGd() {
        return client.executeRequest(get(GET_GD_URL));
    }

    public CompletableFuture<Response> getHttpBinOrg() {
        return client.executeRequest(get(GET_HTTPBIN_URL));
    }
}

