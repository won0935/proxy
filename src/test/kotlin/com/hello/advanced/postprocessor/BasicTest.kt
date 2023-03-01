package com.hello.advanced.postprocessor

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {

    @Test
    fun basicConfig() {
        val context = AnnotationConfigApplicationContext(BasicConfig::class.java)

        //A는 빈으로 등록된다.
        val a = context.getBean("beanA", A::class.java)
        a.hello()

        //B는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { context.getBean(B::class.java) }
    }

    @Configuration
    class BasicConfig {
        @Bean("beanA")
        fun a(): A {
            return A()
        }
    }

    class A {
        private val log = KotlinLogging.logger {}
        fun hello() {
            log.info { "hello A" }
        }
    }

    class B {
        private val log = KotlinLogging.logger {}
        fun hello() {
            log.info { "hello B" }
        }
    }
}
