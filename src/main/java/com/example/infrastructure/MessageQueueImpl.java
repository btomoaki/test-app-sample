package com.example.infrastructure;

import java.util.Objects;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.domain.MessageQueue;

@Component
public class MessageQueueImpl implements MessageQueue {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public boolean send(String queieName, String message) {
        template.convertAndSend(queue.getName(), message);
        return true;
    };

    public String consume(String queieName) {
        Message msg = template.receive(queue.getName());
        if (Objects.nonNull(msg)) {
            return new String(msg.getBody());
        }
        return "";
    };
}
