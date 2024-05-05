package com.polimerconsumer.springgitapi.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.github.com")
            .build()
    }
}