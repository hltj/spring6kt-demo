package me.hltj.demo.spring6kt.service;

import jakarta.annotation.Nonnull;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface JUserService {
    Mono<JUser> register(@Nonnull String name);

    record JUser(int id, @Nonnull String name, @Nonnull Instant regTime) {
    }
}
