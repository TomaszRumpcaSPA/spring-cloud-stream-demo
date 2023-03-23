package com.example.consumer

import com.example.consumer.model.Payment
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.SubscribableChannel

@EnableBinding(DunningPaymentCloudStream.Binding::class)
abstract class DunningPaymentCloudStream {

    @StreamListener(Binding.INPUT)
    fun _handle(message: Payment) {
        handle(message)
    }

    abstract fun handle(message: Payment)

    interface Binding {

        @Input(INPUT)
        fun logPayment(): SubscribableChannel

        companion object {
            const val INPUT = "LogDunningPaymentIn"
        }
    }

}