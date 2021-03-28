package com.unipi.stavrosvl7.faceit.messaging;

import com.unipi.stavrosvl7.faceit.controllers.UserController;
import com.unipi.stavrosvl7.faceit.models.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class EventConsumer {

    private final UserController userController;
    private final Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    public EventConsumer(UserController userController) {
        this.userController = userController;
    }

    @StreamListener(EventStream.INBOUND)
    public void consumeEvent(@Payload User user) {

        LOGGER.info("Event consumed successfully ! A new user has been added with nickname: {} email: {}",user.getNickName(),user.getEmail());

        Map<String,String> result = userController.getUsers().stream().collect(Collectors.toMap(User::getEmail,User::getNickName));

        JSONObject json = new JSONObject(result);

        LOGGER.info("Microservice response {} ",json);

    }

}
