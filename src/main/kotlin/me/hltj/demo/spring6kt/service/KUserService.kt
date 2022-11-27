package me.hltj.demo.spring6kt.service

import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

interface KUserService {
    suspend fun register(name: String): KUser

    suspend fun getCountByName(name: String): Int

    fun getCountByNameMono(name: String): Mono<Int>

    fun getAllByName(name: String): Flow<KUser>

    fun getAllByNameFlux(name: String): Flux<KUser>

    suspend fun serialPreview(name: String): KPreview
}

@JvmRecord
data class KUser(val id: Int, val name: String, val regTime: Instant)

@JvmRecord
data class KPreview(val count: Int, val first: KUser?)
