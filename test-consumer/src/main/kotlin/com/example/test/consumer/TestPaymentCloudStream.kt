package com.example.test.consumer

import com.example.test.consumer.model.Payment
import mu.KotlinLogging
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.SubscribableChannel

private val logger = KotlinLogging.logger {}

@EnableBinding(TestPaymentCloudStream.Binding::class)
class TestPaymentCloudStream {

    @StreamListener(Binding.INPUT)
    fun _handle(message: Payment) {
        logger.info { "received payment $message" }
    }

    interface Binding {

        @Input(INPUT)
        fun logPayment(): SubscribableChannel

        companion object {
            const val INPUT = "LogTestPaymentIn"
        }
    }

}