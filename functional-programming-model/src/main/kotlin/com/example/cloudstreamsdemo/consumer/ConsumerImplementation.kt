package com.example.cloudstreamsdemo.consumer

import com.example.model.Payment
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class ConsumerImplementation: Consumer() {

    override fun handle(event: Payment) {
        logger.info { "Received $event" }
    }
}
