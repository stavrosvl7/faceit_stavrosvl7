package com.unipi.stavrosvl7.faceit.messaging;

import com.unipi.stavrosvl7.faceit.models.User;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventConsumer {

    private final RestTemplate restTemplate;

    public EventConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @StreamListener(EventStream.INBOUND)
    public void consumeEvent(@Payload User user) {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/api/users/all", String.class);
        System.out.println("Event consumed successfully ! A new user has been added with nickname: " + user.getNickName() + " email: "
                + user.getEmail());
        System.out.println("Microservice response " + response.getBody());
    }

}
