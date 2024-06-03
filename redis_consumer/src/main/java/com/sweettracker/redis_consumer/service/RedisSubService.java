package com.sweettracker.redis_consumer.service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSubService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisMessageListenerContainer redisContainer;
    private final MessageListenerAdapter messageListener;
    private static final String TOPICS_KEY = "pubsub::topics";

    @PostConstruct
    public void init () {
        Set<String> topicNames = redisTemplate.opsForSet().members(TOPICS_KEY);
        if (topicNames != null) {
            for (String topicName : topicNames) {
                ChannelTopic topic = new ChannelTopic(topicName);
                redisContainer.addMessageListener(messageListener, topic);
            }
        }
    }
}
