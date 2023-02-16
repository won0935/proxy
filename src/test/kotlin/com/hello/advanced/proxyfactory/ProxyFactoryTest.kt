package com.hello.advanced.proxyfactory

import com.hello.advanced.common.advice.TimeAdvice
import com.hello.advanced.common.service.ConcreteService
import com.hello.advanced.common.service.ServiceImpl
import com.hello.advanced.common.service.ServiceInterface
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils


class ProxyFactoryTest {

    val log = KotlinLogging.logger {}

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    fun interfaceProxy() {

        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice()) //advice : 프록시가 제공하는 부가 기능

        val proxy = proxyFactory.proxy as ServiceInterface

        log.info { "target ${target.javaClass}" }
        log.info { "proxy ${proxy.javaClass}" }

        proxy.save()

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isFalse

    }

    @Test
    @DisplayName("인터페이스가 없으면 CGLIB 프록시 사용")
    fun cglibProxy() {

        val target = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice()) //advice : 프록시가 제공하는 부가 기능

        val proxy = proxyFactory.proxy as ConcreteService

        log.info { "target ${target.javaClass}" }
        log.info { "proxy ${proxy.javaClass}" }

        proxy.call()

        Assertions.assertThat(AopUtils.isAopProxy(proxy)).isTrue
        Assertions.assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse
        Assertions.assertThat(AopUtils.isCglibProxy(proxy)).isTrue
    }

}
