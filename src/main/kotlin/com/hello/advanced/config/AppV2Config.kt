package com.hello.advanced.config

import com.hello.advanced.app.v2.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController

@Configuration
class AppV2Config {

//    @Bean
//    fun orderControllerV2(): OrderControllerV2 {
//        return OrderControllerV2(orderServiceV2())
//    }

    @Bean
    fun orderServiceV2(): OrderServiceV2 {
        return OrderServiceV2(orderRepositoryV2())
    }

    @Bean
    fun orderRepositoryV2(): OrderRepositoryV2 {
        return OrderRepositoryV2()
    }

}
