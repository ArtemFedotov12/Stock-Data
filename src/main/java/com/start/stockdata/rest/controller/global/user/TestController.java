package com.start.stockdata.rest.controller.global.user;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.stockdata.identity.dto.response.FieldResponseDto;

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
    public ResponseEntity<FieldResponseDto> greeting3() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.OK);
    }

    @GetMapping("/hello4")
    public ResponseEntity<FieldResponseDto> greeting4() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/hello5")
    public ResponseEntity<FieldResponseDto> greeting5() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/hello6")
    public ResponseEntity<FieldResponseDto> greeting6() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/hello7")
    public ResponseEntity<FieldResponseDto> greeting7() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.OK);
    }

    @GetMapping("/hello8")
    public ResponseEntity<FieldResponseDto> greeting8() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/hello9")
    public ResponseEntity<String> greeting9() {
        return new ResponseEntity<>("lol", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/hello10")
    public ResponseEntity<FieldResponseDto> greeting10() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.PERMANENT_REDIRECT);
    }

    @GetMapping("/hello11")
    public ResponseEntity<FieldResponseDto> greeting11() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.TEMPORARY_REDIRECT);
    }

    @GetMapping("/hello12")
    public ResponseEntity<FieldResponseDto> greeting12() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.CREATED);
    }

    @GetMapping("/hello13")
    public ResponseEntity<FieldResponseDto> greeting13() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.CONTINUE);
    }

    @GetMapping("/hello14")
    public ResponseEntity<FieldResponseDto> greeting14() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.PROCESSING);
    }

    @GetMapping("/hello15")
    public ResponseEntity<FieldResponseDto> greeting15() {
        return new ResponseEntity<>(new FieldResponseDto("asset1", "display1"), HttpStatus.UNAUTHORIZED);
    }
}
