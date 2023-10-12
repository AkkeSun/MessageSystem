package com.example.kafkaConsumer.consumer;

import com.example.kafkaConsumer.domain.MyData;
import com.example.kafkaConsumer.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(topics = "test-topic", groupId = "group1")
    public void consumeMyopic1(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
        MyData myData = jsonUtil.jsonToObject(message, MyData.class);
        log.info("[return] <== " + myData.toString(), partition);
    }
}
