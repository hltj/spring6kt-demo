package me.hltj.demo.spring6kt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/j")
public class JSimpleController {
    @GetMapping("/hello")
    String hello() {
        return "Hello Spring 6 & Java 17";
    }

    @GetMapping("/3s")
    Mono<String> delay3s() {
        return Mono.just("Delayed 3s").delayElement(Duration.ofSeconds(3));
    }
}
