package com.start.stockdata.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public  String greeting() {
        return "Hello, World";
    }

    @GetMapping("/hello2")
    public  String greeting2() {
        return "Hello, World";
    }
}
