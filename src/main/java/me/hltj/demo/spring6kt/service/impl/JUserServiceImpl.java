package me.hltj.demo.spring6kt.service.impl;

import jakarta.annotation.Nonnull;
import me.hltj.demo.spring6kt.model.JUserEntity;
import me.hltj.demo.spring6kt.repository.JUserRepository;
import me.hltj.demo.spring6kt.service.JUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class JUserServiceImpl implements JUserService {
    private final JUserRepository jUserRepository;

    public JUserServiceImpl(JUserRepository jUserRepository) {
        this.jUserRepository = jUserRepository;
    }

    @Override
    public Mono<JUser> register(@Nonnull String name) {
        return jUserRepository.save(new JUserEntity(null, name, Instant.now())).map(entity ->
                new JUser(entity.id(), entity.name(), entity.regTime())
        );
    }

    @Override
    public Mono<Integer> getCountByName(@Nonnull String name) {
        return jUserRepository.countByName(name);
    }

    @Override
    public Flux<JUser> getAllByName(@Nonnull String name) {
        return jUserRepository.findAllByName(name);
    }

    @Override
    public Mono<JPreview> serialPreview(@Nonnull String name) {
        return jUserRepository.countByName(name).flatMap(count ->
                jUserRepository.getFirst1ByName(name).map(first ->
                        new JPreview(count, first)
                ).defaultIfEmpty(new JPreview(count, null))
        );
    }
}
