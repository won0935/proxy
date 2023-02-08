package com.hello.advanced.config.v1_proxy

import com.hello.advanced.app.v2.OrderControllerV2
import com.hello.advanced.app.v2.OrderRepositoryV2
import com.hello.advanced.app.v2.OrderServiceV2
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import com.hello.advanced.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import com.hello.advanced.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV2 {
        val orderServiceV2 = orderService(logTrace)
        val orderControllerV2 = OrderControllerV2(orderServiceV2)
        return OrderControllerConcreteProxy(orderControllerV2, orderServiceV2, logTrace)
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV2 {
        val orderRepositoryV2 = orderRepository(logTrace)
        val orderServiceV2 = OrderServiceV2(orderRepositoryV2)
        return OrderServiceConcreteProxy(orderServiceV2, orderRepositoryV2, logTrace)
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepositoryV2 = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(orderRepositoryV2, logTrace)
    }

}
