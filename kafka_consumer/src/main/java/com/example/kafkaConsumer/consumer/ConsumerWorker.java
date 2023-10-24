package com.example.kafkaConsumer.consumer;

import java.util.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerWorker {

    /*
        1. 같은 topic 이라도 group 이 다르면 offset 이 다르게 찍히기 때문에 동일한 record 로 다른 작업을 수행할 수 있다
        2. auto commit 의 경우 데이터 중복이 발생하거나 로직 실패시 재시도가 어려우므로 수동 커밋 작업을 진행했다
     */
    @KafkaListener(topics = "od",  containerFactory = "kafkaListenerContainerFactory1")
    public void consumer1(@Payload String message, Acknowledgment acknowledgment){
        log.info("       [group 1 return] <== " + message);
        acknowledgment.acknowledge(); // 수동 커밋
    }

    // concurrency = thread 수 (파티션 수와 일치시키는게 가장 이상적이다)
    @KafkaListener(topics = "od", containerFactory = "kafkaListenerContainerFactory2", concurrency = "2")
    public void consumer2(@Payload String message, Acknowledgment acknowledgment) {
        log.info("[group 2 return] <== " + message);
        acknowledgment.acknowledge();
    }




    /*
        batch Listener
        1. 데이터 저장시 bulk insert 에 용의
        2. 외부 API 호출시 traffic control 에 용의
     */
    @KafkaListener(topics = "od", containerFactory = "batchListenerFactory")
    public void batchConsumer(ConsumerRecords<String, String> records, Acknowledgment acknowledgment) {
        try{
            Thread.sleep(5000);
            log.info("[bulk insert] -- " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                log.info("[batch item] -- " + record.value());
            }
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.info("[batch return] 예외발생 -- " + e.getMessage());
        }
    }

}
