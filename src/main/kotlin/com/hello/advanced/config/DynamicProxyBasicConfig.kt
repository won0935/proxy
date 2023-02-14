package com.hello.advanced.config

import com.hello.advanced.app.v1.*
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyBasicConfig {

    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val target = OrderControllerV1Impl(orderServiceV1(logTrace)) // 프록시 객체 반환
        val handler = LogTraceBasicHandler(target, logTrace)

        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader,
            arrayOf<Class<*>>(OrderControllerV1::class.java),
            handler
        ) as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val target = OrderServiceV1Impl(orderRepositoryV1(logTrace)) // 프록시 객체 반환
        val handler = LogTraceBasicHandler(target, logTrace)

        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader,
            arrayOf<Class<*>>(OrderServiceV1::class.java),
            handler
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val target = OrderRepositoryV1Impl()
        val handler = LogTraceBasicHandler(target, logTrace)

        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader,
            arrayOf<Class<*>>(OrderRepositoryV1::class.java),
            handler
        ) as OrderRepositoryV1
    }

    private fun getProxy(handler: LogTraceBasicHandler, interFace: Class<*>): Any {
        return Proxy.newProxyInstance(
            interFace::class.java.classLoader,
            arrayOf<Class<*>>(interFace::class.java),
            handler
        )
    }

}
