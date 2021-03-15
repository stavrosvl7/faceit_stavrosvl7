package com.unipi.stavrosvl7.faceit.services;

import com.unipi.stavrosvl7.faceit.messaging.EventStream;
import com.unipi.stavrosvl7.faceit.models.User;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class EventStreamService {

    private final EventStream eventStream;

    public EventStreamService(EventStream eventStream) {
        this.eventStream = eventStream;
    }

    public Boolean produceEvent(User user) {
        System.out.println("Producing event for user with id: "+ user.getId() +" name: "+user.getFirstName()+" email: "+ user.getEmail());
        user.setBytePayload(user.getEmail().getBytes());
        MessageChannel messageChannel = eventStream.producer();
        return messageChannel.send(MessageBuilder.withPayload(user)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());

    }

}
