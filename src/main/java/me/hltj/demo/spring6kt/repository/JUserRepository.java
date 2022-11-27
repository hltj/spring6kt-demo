package me.hltj.demo.spring6kt.repository;

import lombok.NonNull;
import me.hltj.demo.spring6kt.model.JUserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface JUserRepository extends ReactiveCrudRepository<JUserEntity, @NonNull Integer> {
}
