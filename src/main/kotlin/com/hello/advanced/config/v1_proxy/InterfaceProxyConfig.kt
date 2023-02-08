package com.hello.advanced.config.v1_proxy

import com.hello.advanced.app.v1.*
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.ThreadLocalLogTrace
import com.hello.advanced.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import com.hello.advanced.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import com.hello.advanced.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {

    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val orderControllerV1Impl = OrderControllerV1Impl(orderService(logTrace))
        return OrderControllerInterfaceProxy(orderControllerV1Impl, logTrace)
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val orderServiceV1Impl = OrderServiceV1Impl(orderRepository(logTrace))
        return OrderServiceInterfaceProxy(orderServiceV1Impl, logTrace)
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepositoryV1Impl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(orderRepositoryV1Impl, logTrace)
    }

}
