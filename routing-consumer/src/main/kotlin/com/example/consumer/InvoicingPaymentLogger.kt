package com.example.consumer

import com.example.consumer.model.Payment
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class InvoicingPaymentLogger: InvoicingPaymentCloudStream() {

    override fun handle(payemnt: Payment) {
        logger.info { "Invoicing payment received: $payemnt" }
    }
}