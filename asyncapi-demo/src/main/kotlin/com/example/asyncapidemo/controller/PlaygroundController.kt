package com.example.asyncapidemo.controller

import com.finion.asyncapi.PaymentCloudStreamPublisher
import com.finion.asyncapi.models.Payment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/")
class PlaygroundController(
    val publisher: PaymentCloudStreamPublisher,
) {

    @PostMapping
    fun sendMessage(): ResponseEntity<Unit> {
        println("executing")
        publisher.publish(Payment(BigDecimal.TEN, "payer", "remi", "receiver", LocalDate.now(), "abc"))
        return ResponseEntity.ok().build()
    }

}
