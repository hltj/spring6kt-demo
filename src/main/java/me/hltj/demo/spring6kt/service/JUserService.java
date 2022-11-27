package me.hltj.demo.spring6kt.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface JUserService {
    Mono<JUser> register(@Nonnull String name);

    Mono<Integer> getCountByName(@Nonnull String name);

    Flux<JUser> getAllByName(@Nonnull String name);

    Mono<JPreview> serialPreview(@Nonnull String name);

    record JUser(int id, @Nonnull String name, @Nonnull Instant regTime) {
    }

    record JPreview(int count, @Nullable JUser first) {
    }
}
