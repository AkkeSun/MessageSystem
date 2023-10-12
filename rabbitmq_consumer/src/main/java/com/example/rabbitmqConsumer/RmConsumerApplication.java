package com.example.rabbitmqConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RmConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmConsumerApplication.class, args);
    }
}