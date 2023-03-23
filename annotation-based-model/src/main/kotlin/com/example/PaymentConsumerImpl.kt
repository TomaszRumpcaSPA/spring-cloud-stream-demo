package com.example

import com.example.model.Payment
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class PaymentConsumerImpl: PaymentConsumer() {

    override fun handle(message: Payment) {
        logger.info { "received: $message" }
    }
}