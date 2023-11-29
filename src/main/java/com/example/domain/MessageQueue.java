package com.example.domain;

public interface MessageQueue {

    public boolean send(String queieName, String message);

    public String consume(String queieName);
}
