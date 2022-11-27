package me.hltj.demo.spring6kt.service

import java.time.Instant

interface KUserService {
    suspend fun register(name: String): KUser
}

@JvmRecord
data class KUser(val id: Int, val name: String, val regTime: Instant)
