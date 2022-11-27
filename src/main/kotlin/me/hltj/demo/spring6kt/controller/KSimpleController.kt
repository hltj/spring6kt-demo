package me.hltj.demo.spring6kt.controller

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.time.Duration

@RestController
@RequestMapping("/k")
class KSimpleController {
    @GetMapping("/hello")
    fun hello() = "Hello Spring 6 & Kotlin 1.7"

    @GetMapping("/3s")
    suspend fun delay3s() = with(Duration) {
        delay(3.seconds)
        "Delayed 3s"
    }
}
