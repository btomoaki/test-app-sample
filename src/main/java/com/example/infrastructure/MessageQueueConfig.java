package com.example.infrastructure;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.domain.MessageQueue;

@Configuration
public class MessageQueueConfig {

    @Value("${web_test.mq.queue.name:test}")
    private String queue_name;

    @Bean
    public Queue getQueue() {
        return new Queue(queue_name);
    }

}
