package com.example.services;

import com.example.dao.SampleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class SampleService {
    private static Logger LOGGER = LoggerFactory.getLogger(SampleClient.class);

    private final SampleClient client;

    @Inject
    public SampleService(SampleClient client) {
        this.client = client;
    }

    public void fetchAllService() {
        CompletableFuture.allOf(client.getProductBundle(),
                client.getHttpBinOrg(),
                client.getWsGetGd()).join();
        LOGGER.debug("all service has been fetch");
    }
}
