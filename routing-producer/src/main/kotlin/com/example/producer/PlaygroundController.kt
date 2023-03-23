package com.example.producer

import com.example.producer.model.Payment
import com.example.producer.PaymentCloudStreamPublisher
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/")
class PlaygroundController(
    val publisher: PaymentCloudStreamPublisher,
) {

    @PostMapping
    fun sendMessage(@RequestBody body: String): ResponseEntity<String> {
        logger.info { "publishing $body" }
        val payment = Payment(BigDecimal("100"), "Payer", "my $body payment", "Receiver", LocalDate.now(), "e2eid")
        publisher.publishPayment(payment, body)
        return ResponseEntity.ok().body(body)
    }

}