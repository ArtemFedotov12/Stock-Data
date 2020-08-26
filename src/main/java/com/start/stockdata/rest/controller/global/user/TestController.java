package com.start.stockdata.rest.controller.global.user;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public  String greeting() {
        long startTime = System.nanoTime();

        int k = 0;
        int counter = 0;

        for (int i = 0; i < 1_000_000_00; i++) {
            k += ThreadLocalRandom.current().nextInt(1, 6 + 1);
            k -= ThreadLocalRandom.current().nextInt(1, 6 + 1);
            k += ThreadLocalRandom.current().nextInt(1, 6 + 1);
            k -= ThreadLocalRandom.current().nextInt(1, 6 + 1);
            k += ThreadLocalRandom.current().nextInt(1, 6 + 1);
            counter++;
            if (counter % 1_000_000_0 == 0) {
                System.out.println("Thread: " + Thread.currentThread().getId());
            }
        }

        double estimatedTime = (System.nanoTime() - startTime) / 1_000_000_000.0;

        return String.format("K = %s\nTime = %s\n Thread = %s", k, estimatedTime, Thread.currentThread().getId());
    }

    @GetMapping("/hello2")
    public  String greeting2() {
        return "Hello, World";
    }

    @GetMapping("/hello3")
    public ResponseEntity<String> greeting3() {
        return new ResponseEntity<>("Hello, World", HttpStatus.OK);
    }

}
