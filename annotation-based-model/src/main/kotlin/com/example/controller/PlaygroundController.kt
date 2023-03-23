package com.example.cloudstreamsdemo.controller

import com.example.PaymentPublisher
import com.example.model.Payment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/")
class PlaygroundController(
    val publisher: PaymentPublisher,
) {

    @PostMapping
    fun sendMessage(@RequestBody body: String): ResponseEntity<Unit> {
        publisher.publish(Payment(BigDecimal.TEN, "payer", "remi", "receiver", LocalDate.now(), "abc"))
        return ResponseEntity.ok().build()
    }

}
