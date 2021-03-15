package com.unipi.stavrosvl7.faceit.messaging;

import com.unipi.stavrosvl7.faceit.controllers.UserController;
import com.unipi.stavrosvl7.faceit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventConsumer {

    private final RestTemplate restTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    public EventConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @StreamListener(EventStream.INBOUND)
    public void consumeEvent(@Payload User user) {

        LOGGER.info("Event consumed successfully ! A new user has been added with nickname: {} email: {}",user.getNickName(),user.getEmail());

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/api/users/all", String.class);

        LOGGER.info("Microservice response {} ",response.getBody());

    }

}
