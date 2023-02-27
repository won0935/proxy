package com.hello.advanced.advisor

import com.hello.advanced.common.service.ServiceImpl
import com.hello.advanced.common.service.ServiceInterface
import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {


    @Test
    @DisplayName("팩토리 여러개 생성")
    fun multiAdvisorTest1() {

        //proxy1생성
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        proxyFactory.addAdvisor(advisor)
        val proxy1 = proxyFactory.proxy as ServiceInterface

        //proxy2생성
        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)

        val proxy2 = proxyFactory2.proxy as ServiceInterface

        proxy2.save()
        proxy2.find()
    }

    @Test
    @DisplayName("팩토리 하나에 추가")
    fun multiAdvisorTest2() {

        //proxy1생성
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())

        proxyFactory.addAdvisor(advisor)
        proxyFactory.addAdvisor(advisor2)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }

    class Advice1 : MethodInterceptor {
        private val log = KotlinLogging.logger {}

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info { "Advice1 호출!" }
            return invocation.proceed()
        }
    }

    class Advice2 : MethodInterceptor {
        private val log = KotlinLogging.logger {}

        override fun invoke(invocation: MethodInvocation): Any? {
            log.info { "Advice2 호출!" }
            return invocation.proceed()
        }
    }


}
