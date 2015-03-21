package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}

@Controller
class Hello implements CommandLineRunner {
    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Hello!!");
    }
}
