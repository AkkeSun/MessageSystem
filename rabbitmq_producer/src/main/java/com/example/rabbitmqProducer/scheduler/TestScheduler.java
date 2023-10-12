package com.example.rabbitmqProducer.scheduler;

import com.example.rabbitmqProducer.domain.User;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class TestScheduler {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;

    public TestScheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = "myExchange";
    }

    // 문자열 전송
    @Scheduled(fixedRate = 1000)
    public void textSend() {
        String nowDate = LocalDateTime.now().toString();
        log.info("[textSend] ==> " + nowDate);

        // rabbitmq 전송 (exchange 이름, routing Key, Request data)
        rabbitTemplate.convertAndSend(exchange, "mail_1_key", nowDate);
    }


    // 객채 전송 (Jackson2JsonMessageConverter Bean 생성 필요)
    @Scheduled(fixedRate = 1100)
    public  void dtoSend() {
        String nowDate = LocalDateTime.now().toString();
        User user = User.builder().name("od").date(nowDate).build();
        log.info("    [dtoSend] ==> " + user.toString());

        // rabbitmq 전송 (exchange 이름, routing Key, Request data)
        rabbitTemplate.convertAndSend(exchange, "mail_2_key", user);
    }
}
