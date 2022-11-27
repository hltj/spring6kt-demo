package me.hltj.demo.spring6kt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/j")
public class JSimpleController {
    @GetMapping("/hello")
    String hello() {
        return "Hello Spring 6 & Java 17";
    }
}
