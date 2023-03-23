package com.example.producer

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


//@Configuration
class RabbitConfig {

    @Bean
    fun exchangeCustomizer(): DeclarableCustomizer {
        return DeclarableCustomizer { declarable ->
            if (declarable is Exchange) {
                if (declarable.name.equals("payment-exchange")) {
                    declarable.addArgument("alternate-exchange", "payment-alternate-exchange")
                }
            }
            declarable
        }
    }

    @Bean
    fun paymentAlternateExchange(): Declarables {
        val errorQueue = Queue("error-queue", true)
        val fanoutExchange = FanoutExchange("payment-alternate-exchange")
        return Declarables(
            errorQueue,
            fanoutExchange,
            BindingBuilder.bind(errorQueue).to(fanoutExchange),
        )
    }
}

