package me.hltj.demo.spring6kt.repository

import kotlinx.coroutines.flow.Flow
import me.hltj.demo.spring6kt.model.KUserEntity
import me.hltj.demo.spring6kt.service.KUser
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface KUserRepository : ReactiveCrudRepository<KUserEntity, Int> {
    suspend fun countByName(name: String): Int

    fun findAllByName(name: String): Flow<KUser>
}
