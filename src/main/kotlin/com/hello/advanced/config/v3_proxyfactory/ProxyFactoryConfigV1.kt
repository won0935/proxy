package com.hello.advanced.config.v3_proxyfactory

import com.hello.advanced.app.v1.*
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
class ProxyFactoryConfigV1 {

    private val log = KotlinLogging.logger {}

    @Bean
    fun orderControllerV1(logTrace: LogTrace) : OrderControllerV1{
        val orderControllerV1 = OrderControllerV1Impl(orderServiceV1(logTrace))
        val factory = ProxyFactory(orderControllerV1)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderControllerV1
        log.info { "ProxyFactory proxy = ${proxy.javaClass}" }

        return proxy
    }


    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderServiceV1 = OrderServiceV1Impl(orderRepositoryV1(logTrace))
        val factory = ProxyFactory(orderServiceV1)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderServiceV1
        log.info { "ProxyFactory proxy = ${proxy.javaClass}" }

        return proxy
    }


    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepositoryV1 = OrderRepositoryV1Impl()
        val factory = ProxyFactory(orderRepositoryV1)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy = factory.proxy as OrderRepositoryV1
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
