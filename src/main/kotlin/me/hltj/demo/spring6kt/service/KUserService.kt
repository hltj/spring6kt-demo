package me.hltj.demo.spring6kt.service

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface KUserService {
    suspend fun register(name: String): KUser

    suspend fun getCountByName(name: String): Int

    fun getAllByName(name: String): Flow<KUser>
}

@JvmRecord
data class KUser(val id: Int, val name: String, val regTime: Instant)
