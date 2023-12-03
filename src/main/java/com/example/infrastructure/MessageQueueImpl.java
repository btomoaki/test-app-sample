package com.example.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.domain.MessageQueue;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Component
public class MessageQueueImpl implements MessageQueue {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    private Map<String, ConnectionFactory> mqCon = new HashMap<>();

    public boolean send(String queieName, String message) {
        template.setConnectionFactory(this.getConnection("con1"));
        template.convertAndSend(queue.getName(), message + "1");
        
        template.setConnectionFactory(this.getConnection("con2"));
        template.convertAndSend(queue.getName(), message + "a");
        template.setConnectionFactory(this.getConnection("con3"));
        template.convertAndSend(queue.getName(), message + "x");
        return true;
    };

    public String consume(String queieName) {
        template.setConnectionFactory(this.getConnection("con1"));
        Message msg = template.receive(queue.getName());
        template.setConnectionFactory(this.getConnection("con2"));
        msg = template.receive(queue.getName());
        template.setConnectionFactory(this.getConnection("con3"));
        msg = template.receive(queue.getName());
        if (Objects.nonNull(msg)) {
            return new String(msg.getBody());
        }
        return "";
    };

    public ConnectionFactory createConnection(String host, String username, String password,
            int port) {

        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setHost(host);
        cf.setUsername(username);
        cf.setPassword(password);
        // cf.setVirtualHost(vhost);
        cf.setPort(port);

        return cf;

    }

    private ConnectionFactory getConnection(String conName) {
        ConnectionFactory con = mqCon.get(conName);
        if (Objects.nonNull(con)) {
            return con;
        }
        return CreateConnectionFactory(conName);
    }

    private ConnectionFactory CreateConnectionFactory(String conName) {
        switch (conName) {
            case "con1":
                return this.createConnection("localhost", "test", "test", 5672);
            case "con2":
                return this.createConnection("mylocalhost", "test", "test", 5672);
            case "con3":
                return this.createConnection("ilocalhost", "test", "test", 5672);
            default:
                return null;
        }

    }
}