package me.hltj.demo.spring6kt.controller

import me.hltj.demo.spring6kt.service.KUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/k/users")
class KUserController(private val kUserService: KUserService) {
    @PostMapping("")
    suspend fun register(@RequestParam name: String) = kUserService.register(name)

    @GetMapping("/_count")
    suspend fun getCountByName(@RequestParam name: String) = kUserService.getCountByName(name)

    @GetMapping("")
    fun getMulti(@RequestParam name: String) = kUserService.getAllByName(name)
}
