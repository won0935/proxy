package com.hello.advanced.config.v3_proxyfactory

import com.hello.advanced.app.v2.OrderControllerV2
import com.hello.advanced.app.v2.OrderRepositoryV2
import com.hello.advanced.app.v2.OrderServiceV2
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import mu.KotlinLogging
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV2 {

    private val log = KotlinLogging.logger {}

    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderControllerV2 = OrderControllerV2(orderServiceV2(logTrace))
        val factory = ProxyFactory(orderControllerV2)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderControllerV2
        log.info { "ProxyFactory proxy = ${proxy.javaClass}" }

        return proxy
    }


    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderServiceV2 = OrderServiceV2(orderRepositoryV2(logTrace))
        val factory = ProxyFactory(orderServiceV2)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderServiceV2
        log.info { "ProxyFactory proxy = ${proxy.javaClass}" }

        return proxy
    }


    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepositoryV2 = OrderRepositoryV2()
        val factory = ProxyFactory(orderRepositoryV2)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderRepositoryV2
        log.info { "ProxyFactory proxy = ${proxy.javaClass}" }
        return proxy
    }

    fun getAdvisor(logTrace: LogTrace): Advisor {

        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)

    }

}
