package com.example

import com.example.model.Payment
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Output
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.MessageChannel
import org.springframework.stereotype.Component

@Component
@EnableBinding(PaymentPublisher.Binding::class)
class PaymentPublisher(private val binding: Binding) {

    fun publishAfterCommit(payment: Payment){
        // TODO after commit
        binding.allocatedSchedules().send(MessageBuilder.withPayload(payment).build())
    }

    fun publish(payment: Payment){
        binding.allocatedSchedules().send(MessageBuilder.withPayload(payment).build())
    }

    interface Binding {

        @Output(OUTPUT)
        fun allocatedSchedules(): MessageChannel

        companion object {
            const val OUTPUT = "paymentPublisher-out-0"
        }
    }
}
