package me.hltj.demo.spring6kt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/k")
class KSimpleController {
    @GetMapping("/hello")
    fun hello() = "Hello Spring 6 & Kotlin 1.7"
}
