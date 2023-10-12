package com.example.kafkaProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class kafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(kafkaProducerApplication.class, args);
    }

}