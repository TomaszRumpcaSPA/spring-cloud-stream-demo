package com.example.asyncapidemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class AsyncapiDemoApplication

fun main(args: Array<String>) {
    runApplication<AsyncapiDemoApplication>(*args)
}
