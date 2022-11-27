package me.hltj.demo.spring6kt.controller

import me.hltj.demo.spring6kt.service.KUserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/k/users")
class KUserController(private val kUserService: KUserService) {
    @PostMapping("")
    suspend fun register(@RequestParam name: String) = kUserService.register(name)
}
