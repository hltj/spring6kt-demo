package me.hltj.demo.spring6kt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/j")
@SuppressWarnings("java:S106")
public class JSimpleController {
    @GetMapping("/hello")
    String hello() {
        return "Hello Spring 6 & Java 17";
    }

    @GetMapping("/3s")
    Mono<String> delay3s() {
        return Mono.just("Delayed 3s").delayElement(Duration.ofSeconds(3));
    }

    @GetMapping("/1sx3")
    Flux<Integer> delay1s3times() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.printf("Delayed 1s %d/3%n", i));
    }
}
