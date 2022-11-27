package me.hltj.demo.spring6kt.repository;

import lombok.NonNull;
import me.hltj.demo.spring6kt.model.JUserEntity;
import me.hltj.demo.spring6kt.service.JUserService.JUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JUserRepository extends ReactiveCrudRepository<JUserEntity, @NonNull Integer> {
    Mono<Integer> countByName(@NonNull String name);

    Flux<JUser> findAllByName(@NonNull String name);
}
