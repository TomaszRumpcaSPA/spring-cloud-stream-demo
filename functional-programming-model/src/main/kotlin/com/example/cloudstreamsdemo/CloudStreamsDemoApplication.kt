package com.example.cloudstreamsdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import java.util.function.Consumer
import java.util.function.Supplier


@SpringBootApplication
class CloudStreamsDemoApplication

fun main(args: Array<String>) {
    runApplication<CloudStreamsDemoApplication>(*args)
}
