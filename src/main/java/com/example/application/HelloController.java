package com.example.application;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.WebService;

@RestController
@ComponentScan("com.example.domain")
public class HelloController {

    @Autowired
    private WebService ws;

    @GetMapping("/")
    public String index() throws IOException {
        return ws.getIndex();
    }

    @GetMapping("/health")
    public String health() {
        return ws.getHealth();
    }

    @GetMapping("/sleep")
    public String sleep() {
        return ws.getSleep();
    }

    @GetMapping("/putMessage")
    public String putMessage(PutMessageParams params) {
        if (params.isEmpty()) {
            return ws.putMessage("hello");
        } else {
            return ws.putMessages(params.getMessage(), params.getCount());
        }
    }

    @GetMapping("/getMessage")
    public String getMessage(GetMessageParams params) {
        if (params.isEmpty()) {
            return ws.getMessage();
        } else {
            return ws.getMessages(params.getCount());
        }
    }
}