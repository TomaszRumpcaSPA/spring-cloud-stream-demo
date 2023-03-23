package com.example.producer

import com.example.producer.model.Payment
import mu.KotlinLogging
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Output
import org.springframework.context.ApplicationContext
import org.springframework.integration.amqp.support.ReturnedAmqpMessageException
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ConcurrentHashMap


private val logger = KotlinLogging.logger {}

@Component
@EnableBinding(PaymentCloudStreamPublisher.Binding::class)
class PaymentCloudStreamPublisher(context: ApplicationContext) {

    private final val binding: Binding
    private val returned: Map<UUID, BlockingQueue<ReturnedAmqpMessageException>> = ConcurrentHashMap()
    private val confirms: Map<UUID, BlockingQueue<Boolean>> = ConcurrentHashMap()


    init {
        binding = context.getBean(Binding::class.java)
    }

    fun publishPayment(payment: Payment, paymentClass: String): Boolean {
        val uuid = UUID.randomUUID()
        logger.info { "publishing $paymentClass $uuid" }
        val message = MessageBuilder
            .withPayload<Any>(payment)
            .setHeader("payment-class", paymentClass)
            .setHeader(AmqpHeaders.CORRELATION_ID, uuid.toString())
            .build()
        return binding.classifiedPayments().send(message)
    }

    fun publishPayment(payment: Payment, paymentClass: String, timeoutInMilliseconds: Long): Boolean {
        val message = MessageBuilder
            .withPayload<Any>(payment)
            .setHeader("payment-class", paymentClass)
            .build()
        return binding.classifiedPayments().send(message, timeoutInMilliseconds)
    }

    @ServiceActivator(inputChannel = "payment-confirms")
    fun handleAcks(msg: Message<*>) {
        val correlationId = msg.headers.get(AmqpHeaders.CORRELATION_ID, String::class.java)
        val confirm = msg.headers.get(AmqpHeaders.PUBLISH_CONFIRM, Boolean::class.java)
    }

    @ServiceActivator(inputChannel = "errorChannel")
    fun handleErrors(msg: Message<*>) {
        if (msg.payload is ReturnedAmqpMessageException) {
            val returnedMessage = msg.payload as ReturnedAmqpMessageException
            val correlationId = returnedMessage.amqpMessage.messageProperties.correlationId
        }
    }

    interface Binding {

        @Output(OUTPUT)
        fun classifiedPayments(): MessageChannel

        companion object {
            const val OUTPUT = "PaymentPub"
        }
    }
}


