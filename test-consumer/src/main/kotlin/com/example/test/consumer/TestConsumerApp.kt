package com.example.test.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestConsumerApp

fun main(args: Array<String>) {
    runApplication<TestConsumerApp>(*args)
}

