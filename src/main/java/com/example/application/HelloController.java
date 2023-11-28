package com.example.application;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/data")
    public Map<String, String> getData() {
        Map<String, String> map = Map.of("message", "Hello World!");
        return map;
    }
}