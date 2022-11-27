package me.hltj.demo.spring6kt.controller

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import me.hltj.demo.spring6kt.service.JUserService
import me.hltj.demo.spring6kt.service.JUserService.JUser
import me.hltj.demo.spring6kt.service.KUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/k/users")
class KUserController(private val kUserService: KUserService, private val jUserService: JUserService) {
    @PostMapping("")
    suspend fun register(@RequestParam name: String) = kUserService.register(name)

    @GetMapping("/_count")
    suspend fun getCountByName(@RequestParam name: String) = kUserService.getCountByName(name)

    @GetMapping("")
    fun getMulti(@RequestParam name: String) = kUserService.getAllByName(name)

    @GetMapping("/_j")
    fun getJMulti(@RequestParam name: String): Flow<JUser> = jUserService.getAllByName(name).asFlow()
}
