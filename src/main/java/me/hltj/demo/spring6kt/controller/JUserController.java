package me.hltj.demo.spring6kt.controller;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.reactor.MonoKt;
import kotlinx.coroutines.reactor.ReactorFlowKt;
import me.hltj.demo.spring6kt.service.JUserService;
import me.hltj.demo.spring6kt.service.JUserService.JPreview;
import me.hltj.demo.spring6kt.service.JUserService.JUser;
import me.hltj.demo.spring6kt.service.KUser;
import me.hltj.demo.spring6kt.service.KUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/j/users")
public class JUserController {
    private final JUserService jUserService;
    private final KUserService kUserService;

    public JUserController(JUserService jUserService, KUserService kUserService) {
        this.jUserService = jUserService;
        this.kUserService = kUserService;
    }

    @PostMapping("")
    Mono<JUser> register(@RequestParam String name) {
        return jUserService.register(name);
    }

    @GetMapping("/_count")
    Mono<Integer> getCountByName(@RequestParam String name) {
        return jUserService.getCountByName(name);
    }

    @GetMapping("/_k1_count")
    Mono<Integer> getKt1CountByName(@RequestParam String name) {
        return kUserService.getCountByNameMono(name);
    }

    @GetMapping("/_k2_count")
    Mono<Integer> getKt2CountByName(@RequestParam String name) {
        return MonoKt.mono(EmptyCoroutineContext.INSTANCE, (scope, continuation) ->
                kUserService.getCountByName(name, continuation)
        );
    }

    @GetMapping("")
    Flux<JUser> getMulti(@RequestParam String name) {
        return jUserService.getAllByName(name);
    }

    @GetMapping("/_k1")
    Flux<KUser> getK1Multi(@RequestParam String name) {
        return kUserService.getAllByNameFlux(name);
    }

    @GetMapping("/_k2")
    Flux<KUser> getK2Multi(@RequestParam String name) {
        return ReactorFlowKt.asFlux(kUserService.getAllByName(name));
    }

    @GetMapping("/_preview1")
    Mono<JPreview> serialPreview(@RequestParam String name) {
        return jUserService.serialPreview(name);
    }

    @GetMapping("/_preview2")
    Mono<JPreview> concurrentPreview(@RequestParam String name) {
        return jUserService.concurrentPreview(name);
    }
}
