package com.sweettracker.redis_producer.controller;

import com.sweettracker.redis_producer.service.RedisPubService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisPubController {
    private final RedisPubService redisPubService;

    @PostMapping("/topic/{topicName}")
    public void createTopic(@PathVariable String topicName) {
        redisPubService.createTopic(topicName);
    }

    @DeleteMapping("/topic/{topicName}")
    public void removeTopic(@PathVariable String topicName) {
        redisPubService.removeTopic(topicName);
    }

    @GetMapping("/topics")
    public Map<String, ChannelTopic> getTopics() {
        return redisPubService.getTopics();
    }

    @PostMapping("/publish")
    public void publishMessage(@RequestParam String topicName, @RequestParam String message) {
        redisPubService.publishMessage(topicName, message);
    }
}
