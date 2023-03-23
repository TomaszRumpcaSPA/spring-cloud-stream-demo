package com.example.consumer

import com.example.consumer.model.Payment
import mu.KotlinLogging
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
class DunningPaymentLogger: DunningPaymentCloudStream() {

    override fun handle(payemnt: Payment) {
        logger.info { "Dunning payment received: $payemnt" }
    }
}