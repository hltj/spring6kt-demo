package me.hltj.demo.spring6kt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/j")
@SuppressWarnings("java:S106")
public class JSimpleController {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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

    @SneakyThrows
    @GetMapping("/problem-demo")
    Mono<Void> problemDemo() {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "请求所传 age 与 color 参数不正确");
        pd.setType(URI.create("/problem/invalid-params"));
        pd.setTitle("参数错误");
        pd.setProperty("invalid_params", OBJECT_MAPPER.readValue("""
                [
                  {
                    "name": "age",
                    "reason": "must be a positive integer"
                  },
                  {
                    "name": "color",
                    "reason": "must be 'red', 'green' or 'blue'"
                  }
                ]""", new TypeReference<List<LinkedHashMap<String, Object>>>() {
        }));
        return Mono.just(true).delayElement(Duration.ofSeconds(3)).flatMap(x ->
                Mono.error(new ErrorResponseException(HttpStatus.BAD_REQUEST, pd, null))
        );
    }
}
