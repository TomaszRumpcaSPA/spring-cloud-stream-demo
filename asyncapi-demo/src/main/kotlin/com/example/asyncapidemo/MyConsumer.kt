package com.example.asyncapidemo

import com.finion.asyncapi.PaymentConsumer
import com.finion.asyncapi.models.Payment
import org.springframework.stereotype.Component

@Component
class MyConsumer : PaymentConsumer() {
    override fun handle(event: Payment) {
        println(event)
    }
}