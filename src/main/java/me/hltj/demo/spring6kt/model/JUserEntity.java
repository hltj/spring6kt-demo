package me.hltj.demo.spring6kt.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("users")
public record JUserEntity(@Id Integer id, @Nonnull String name, @Nonnull Instant regTime) {
}
