package com.example.asyncapidemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment


@Configuration
@ComponentScan(basePackages = ["com.finion.asyncapi"])
@PropertySource(
    value = ["classpath:cloud-streams-config.yaml"],
    factory = YamlPropertySourceFactory::class
)
class AsyncApiConfig
