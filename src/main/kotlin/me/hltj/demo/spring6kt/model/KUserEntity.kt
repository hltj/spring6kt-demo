package me.hltj.demo.spring6kt.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("users")
@JvmRecord
data class KUserEntity(@Id val id: Int?, val name: String, val regTime: Instant)
