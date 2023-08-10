package com.example.exp_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ExpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpServerApplication.class, args);
        System.out.println("Hello Java Spring!");
    }
}
