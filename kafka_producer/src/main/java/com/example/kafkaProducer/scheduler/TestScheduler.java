package com.example.kafkaProducer.scheduler;

import com.example.kafkaProducer.domain.MyData;
import com.example.kafkaProducer.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class TestScheduler {

    private final String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final JsonUtil jsonUtil;

    public TestScheduler(KafkaTemplate<String, String> kafkaTemplate, JsonUtil jsonUtil){
        this.topicName = "test-topic";
        this.kafkaTemplate = kafkaTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Scheduled(fixedRate = 1000)
    public void send () {
        String message = jsonUtil.objectToJson(MyData.builder()
            .name("od")
            .time(LocalDateTime.now().toString())
            .build());
        log.info("[send] ==> " + message);
        kafkaTemplate.send(topicName, message);
    }

}
