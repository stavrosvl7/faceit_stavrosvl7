package com.unipi.stavrosvl7.faceit.services;

import com.unipi.stavrosvl7.faceit.messaging.EventConsumer;
import com.unipi.stavrosvl7.faceit.messaging.EventStream;
import com.unipi.stavrosvl7.faceit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class EventStreamService {

    private final EventStream eventStream;
    private final Logger LOGGER = LoggerFactory.getLogger(EventStreamService.class);


    public EventStreamService(EventStream eventStream) {
        this.eventStream = eventStream;
    }

    public Boolean produceEvent(User user) {
        MessageChannel messageChannel = eventStream.producer();
        LOGGER.info("Producing event for user with id: {} name: {} email: {}", user.getId() ,user.getFirstName() ,user.getEmail());
        return messageChannel.send(MessageBuilder.withPayload(user)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());

    }

}
