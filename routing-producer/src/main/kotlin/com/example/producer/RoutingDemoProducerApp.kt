package com.example.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RoutingDemoProducerApp

fun main(args: Array<String>) {
    runApplication<RoutingDemoProducerApp>(*args)
}

