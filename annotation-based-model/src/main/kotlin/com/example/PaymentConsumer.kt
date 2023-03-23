package com.example

import com.example.model.Payment
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Component

@Component
@EnableBinding(PaymentConsumer.Binding::class)
abstract class PaymentConsumer {

    @StreamListener(Binding.INPUT)
    fun handleMessage(message: Payment) {
        handle(message)
    }

    abstract fun handle(message: Payment)

    interface Binding {

        @Input(INPUT)
        fun incomingPayments(): SubscribableChannel

        companion object {
            const val INPUT = "paymentConsumer-in-0"
        }
    }

}