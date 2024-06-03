package com.sweettracker.redis_producer.service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {

    @Getter
    private Map<String, ChannelTopic> topics = new HashMap<>();
    private final RedisTemplate<String, String> redisTemplate;
    private static final String TOPICS_KEY = "pubsub::topics";

    @PostConstruct
    public void init () {
        Set<String> topicNames = redisTemplate.opsForSet().members(TOPICS_KEY);
        if (topicNames != null) {
            for (String topicName : topicNames) {
                topics.put((String) topicName, new ChannelTopic((String) topicName));
                System.out.println("Restored topic: " + topicName);
            }
        }
    }
    public void createTopic(String topicName) {
        if (!topics.containsKey(topicName)) {
            ChannelTopic topic = new ChannelTopic(topicName);
            topics.put(topicName, topic);
            redisTemplate.opsForSet().add(TOPICS_KEY, topicName);
            System.out.println("Created topic: " + topicName);
        } else {
            System.out.println("Topic already exists: " + topicName);
        }
    }

    public void removeTopic(String topicName) {
        ChannelTopic topic = topics.remove(topicName);
        if (topic != null) {
            redisTemplate.opsForSet().remove(TOPICS_KEY, topicName);
            System.out.println("Removed topic: " + topicName);
        } else {
            System.out.println("Topic not found: " + topicName);
        }
    }


    public void publishMessage(String topicName, String message) {
        ChannelTopic topic = topics.get(topicName);
        if (topic != null) {
            redisTemplate.convertAndSend(topic.getTopic(), message); // send
            System.out.println("Published message to topic: " + topicName);
        } else {
            System.out.println("Topic not found: " + topicName);
        }
    }

}
