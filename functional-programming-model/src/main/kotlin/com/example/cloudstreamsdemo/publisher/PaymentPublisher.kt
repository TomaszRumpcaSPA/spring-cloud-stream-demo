package com.example.cloudstreamsdemo.publisher

import com.example.model.Payment
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import java.util.function.Supplier

@Component
class PaymentPublisher : Supplier<Flux<Payment>>{

    private val sink = Sinks.many().unicast().onBackpressureBuffer<Payment>()

    fun publish(event: Payment) {
        sink.emitNext(event, FAIL_FAST)
    }

    override fun get(): Flux<Payment> {
        return sink.asFlux()
    }
}