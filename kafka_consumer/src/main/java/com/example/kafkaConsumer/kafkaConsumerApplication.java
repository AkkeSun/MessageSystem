package com.example.kafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class kafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(kafkaConsumerApplication.class, args);
    }

}
