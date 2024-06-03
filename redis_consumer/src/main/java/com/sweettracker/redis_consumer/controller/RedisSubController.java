package com.sweettracker.redis_consumer.controller;

import com.sweettracker.redis_consumer.service.RedisSubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisSubController {
    private final RedisSubService redisSubService;

    @GetMapping
    public void updateTopic () {
        redisSubService.init();
    }
}
