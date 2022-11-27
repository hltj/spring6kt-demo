package me.hltj.demo.spring6kt.service.impl

import kotlinx.coroutines.reactor.awaitSingle
import me.hltj.demo.spring6kt.model.KUserEntity
import me.hltj.demo.spring6kt.repository.KUserRepository
import me.hltj.demo.spring6kt.service.KUser
import me.hltj.demo.spring6kt.service.KUserService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class KUserServiceImpl(private val kUserRepository: KUserRepository) : KUserService {
    override suspend fun register(name: String): KUser =
        kUserRepository.save(KUserEntity(null, name, Instant.now())).map {
            KUser(it.id!!, it.name, it.regTime)
        }.awaitSingle()

    override suspend fun getCountByName(name: String) = kUserRepository.countByName(name)

    override fun getAllByName(name: String) = kUserRepository.findAllByName(name)
}
