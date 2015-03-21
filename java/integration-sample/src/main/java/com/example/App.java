package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@IntegrationComponentScan
public class App {
    @MessagingGateway
    interface UpperCase {
        @Gateway(requestChannel = "upperCase.input")
        public Collection<String> upperCase(Collection<String> message);
    }

    @Bean
    IntegrationFlow upperCase() {
        return f -> f
                .split()
                .<String, String> transform(String::toUpperCase)
                .aggregate();
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class);

        System.out.println(
                ctx.getBean(UpperCase.class)
                        .upperCase(Arrays.asList("foo", "bar")));
    }
}

