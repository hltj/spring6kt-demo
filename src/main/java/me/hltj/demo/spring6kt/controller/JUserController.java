package me.hltj.demo.spring6kt.controller;

import me.hltj.demo.spring6kt.service.JUserService;
import me.hltj.demo.spring6kt.service.JUserService.JUser;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/j/users")
public class JUserController {
    private final JUserService jUserService;

    public JUserController(JUserService jUserService) {
        this.jUserService = jUserService;
    }

    @PostMapping("")
    Mono<JUser> register(@RequestParam String name) {
        return jUserService.register(name);
    }

    @GetMapping("/_count")
    Mono<Integer> getCountByName(@RequestParam String name) {
        return jUserService.getCountByName(name);
    }

    @GetMapping("")
    Flux<JUser> getMulti(@RequestParam String name) {
        return jUserService.getAllByName(name);
    }
}
