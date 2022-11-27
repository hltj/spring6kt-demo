package me.hltj.demo.spring6kt.repository

import me.hltj.demo.spring6kt.model.KUserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface KUserRepository : ReactiveCrudRepository<KUserEntity, Int>
