package com.example.asyncapidemo

import com.finion.asyncapi.PaymentCloudStream
import com.finion.asyncapi.models.Payment
import org.springframework.stereotype.Component

@Component("paymentCloudStream")
class MyConsumer : PaymentCloudStream() {
    override fun handle(message: Payment) {
        println(message)
    }
}