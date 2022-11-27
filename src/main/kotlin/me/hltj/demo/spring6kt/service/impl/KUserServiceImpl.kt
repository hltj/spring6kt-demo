package me.hltj.demo.spring6kt.service.impl

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import me.hltj.demo.spring6kt.model.KUserEntity
import me.hltj.demo.spring6kt.repository.KUserRepository
import me.hltj.demo.spring6kt.service.KPreview
import me.hltj.demo.spring6kt.service.KUser
import me.hltj.demo.spring6kt.service.KUserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

@Service
class KUserServiceImpl(private val kUserRepository: KUserRepository) : KUserService {
    override suspend fun register(name: String): KUser =
        kUserRepository.save(KUserEntity(null, name, Instant.now())).map {
            KUser(it.id!!, it.name, it.regTime)
        }.awaitSingle()

    override suspend fun getCountByName(name: String) = kUserRepository.countByName(name)

    override fun getCountByNameMono(name: String): Mono<Int> = mono { getCountByName(name) }

    override fun getAllByName(name: String) = kUserRepository.findAllByName(name)

    override fun getAllByNameFlux(name: String): Flux<KUser> = getAllByName(name).asFlux()

    override suspend fun serialPreview(name: String): KPreview {
        val count = kUserRepository.countByName(name)
        val first = kUserRepository.findFirst1ByName(name)
        return KPreview(count, first)
    }

    override suspend fun concurrentPreview(name: String) = coroutineScope {
        val asyncCount = async { kUserRepository.countByName(name) }
        val asyncFirst = async { kUserRepository.findFirst1ByName(name) }
        KPreview(asyncCount.await(), asyncFirst.await())
    }
}
