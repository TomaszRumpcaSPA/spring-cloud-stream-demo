package com.example.cloudstreamsdemo.consumer

import com.example.model.Payment
import java.util.function.Consumer

abstract class Consumer: Consumer<Payment>, ConsumerInterface<Payment> {

    override fun accept(event: Payment) {
        this.handle(event)
    }

}