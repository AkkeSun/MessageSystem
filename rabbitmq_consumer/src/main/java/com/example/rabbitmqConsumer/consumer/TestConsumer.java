package com.example.rabbitmqConsumer.consumer;

import com.example.rabbitmqConsumer.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestConsumer {

    /*
    // bindings 정보를 하나하나 적어줘도 되는데 번거롭다
    @RabbitListener(bindings = @QueueBinding(
             exchange = @Exchange(name = "myExchange", type= ExchangeTypes.DIRECT), // exchange
             key = "mail_1_key", // routing key
             value = @Queue(name="mail_1") // queue name
     ))
     */
    @RabbitListener(queues = "mail_1")
    public void getText(String msg) {  // DTO 로 입력값 매핑
        log.info("[getText] mail_1 " + msg);
    }

    // concurrency = Multi Thread 처리
    @RabbitListener(queues = "mail_2", concurrency = "3")
    public void getObject(User user) throws InterruptedException {
        Thread.sleep(1000);
        log.info("    [getDto] ==> " + user.toString());
    }
}
