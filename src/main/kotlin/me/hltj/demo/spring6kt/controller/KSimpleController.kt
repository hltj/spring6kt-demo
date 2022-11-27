package me.hltj.demo.spring6kt.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import kotlin.time.Duration

@RestController
@RequestMapping("/k")
class KSimpleController {
    private val objectMapper = jacksonObjectMapper()

    @GetMapping("/hello")
    fun hello() = "Hello Spring 6 & Kotlin 1.7"

    @GetMapping("/3s")
    suspend fun delay3s() = with(Duration) {
        delay(3.seconds)
        "Delayed 3s"
    }

    @GetMapping("/1sx3")
    fun delay1s3times() = (1..3).asFlow().onEach {
        with(Duration) { delay(1.seconds) }
        println("Delayed 1s $it/3")
    }

    @GetMapping("/problem-demo")
    suspend fun problemDemo() {
        val pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "请求所传 age 与 color 参数不正确")
        pd.type = URI.create("/problem/invalid-params")
        pd.title = "参数错误"
        pd.setProperty(
            "invalid_params",
            objectMapper.readValue<List<LinkedHashMap<String, Any>>>(
                """
                [
                  {
                    "name": "age",
                    "reason": "must be a positive integer"
                  },
                  {
                    "name": "color",
                    "reason": "must be 'red', 'green' or 'blue'"
                  }
                ]""".trimIndent()
            )
        )
        with(Duration) { delay(3.seconds) }
        throw ErrorResponseException(HttpStatus.BAD_REQUEST, pd, null)
    }
}
