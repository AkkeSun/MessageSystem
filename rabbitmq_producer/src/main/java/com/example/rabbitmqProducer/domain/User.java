package com.example.rabbitmqProducer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
    private String name;
    private String date;
}
