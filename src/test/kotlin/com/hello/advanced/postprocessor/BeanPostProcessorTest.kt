package com.hello.advanced.postprocessor

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest {

    @Test
    fun beanPostProcessorTest() {
        val context = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

        //B객체가 빈으로 등록된다.
        val b = context.getBean("beanA", B::class.java)
        b.hello()

        //A는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { context.getBean(A::class.java) }
    }

    @Configuration
    class BeanPostProcessorConfig {
        @Bean("beanA")
        fun a(): A {
            return A()
        }

        @Bean
        fun aToBPostProcessor(): AtoBPostProcessor {
            return AtoBPostProcessor()
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


    //추가
    class AtoBPostProcessor : BeanPostProcessor {
        private val log = KotlinLogging.logger {}
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
            log.info { "$bean $beanName" }
            if (bean is A)
                return B()
            return bean
        }
    }
}
