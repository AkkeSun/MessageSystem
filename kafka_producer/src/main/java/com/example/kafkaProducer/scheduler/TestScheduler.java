package com.example.kafkaProducer.scheduler;

import com.example.kafkaProducer.domain.MyData;
import com.example.kafkaProducer.utils.JsonUtil;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Configuration
public class TestScheduler {

    private final String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final JsonUtil jsonUtil;

    public TestScheduler(KafkaTemplate<String, String> kafkaTemplate, JsonUtil jsonUtil){
        this.topicName = "od";
        this.kafkaTemplate = kafkaTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Scheduled(fixedRate = 1000)
    public void send () {
        String message = jsonUtil.objectToJson(MyData.builder()
            .name("od")
            .time(LocalDateTime.now().toString())
            .build());

        // 복수의 파티션은 round-robbin 으로 파티션에 전송
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicName, message);

        result.addCallback(new KafkaSendCallback<>(){
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("[send] ==> " + message);
            }
            @Override
            public void onFailure(KafkaProducerException ex) {
                log.error("[send] failed ==> {} | {}",message, ex.toString());
            }
        });
    }

}
