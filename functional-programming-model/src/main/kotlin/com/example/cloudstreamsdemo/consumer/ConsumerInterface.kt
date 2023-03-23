package com.example.cloudstreamsdemo.consumer

interface ConsumerInterface<T> {
    fun handle(event: T)
}