package com.example.domain;

import java.io.IOException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.time.StopWatch;

@Component
@ComponentScan("com.example.infrastructure")
public class WebServiceImpl implements WebService {

    @Value("${web_test.sleep.duretion_sec:0}")
    private long sleep_sec;

    @Value("${inputData:static/index.html}")
    private String index_html;

    @Value("${web_test.mq.queue.name:test}")
    private String queue_name;

    @Autowired
    private FileLoader fr;

    @Autowired
    private MessageQueue mq;

    @Override
    public String getIndex() throws IOException {
        return fr.loadFile(index_html);
    }

    @Override
    public String getHealth() {
        return "ok";
    }

    @Override
    public String getSleep() {
        try {
            Thread.sleep(Duration.ofSeconds(sleep_sec).toMillis());
        } catch (InterruptedException e) {
            return "sleep NG";
        }
        return "sleep OK";
    }

    @Override
    public String putMessage(String message) {
        mq.send(queue_name, message);
        return "send OK";
    }

    @Override
    public String getMessage() {
        String msg = mq.consume(queue_name);
        if (msg.isEmpty()) {
            return "consume OK";
        }
        return "consume OK, message:" + msg;
    }

    @Override
    public String putMessages(String message, int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 1; i <= count; i++) {
            mq.send(queue_name, message + ": " + i);
        }
        stopWatch.stop();
        System.out.println("send OK, message: " + message + ", count: " + count + "time: " + stopWatch.getTime());
        return "send OK, message: " + message + ", count: " + count + ", time: " + stopWatch.getTime() + "ms";
    }

    @Override
    public String getMessages(int count) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int i;
        for (i = 0; i < count; i++) {
            String msg = mq.consume(queue_name);
            if (msg.isEmpty()) {
                break;
            }
        }
        stopWatch.stop();
        System.out.println("send OK, count: " + i + "time: " + stopWatch.getTime());
        return "send OK, count: " + i + ", time: " + stopWatch.getTime() + "ms";
    }
}
