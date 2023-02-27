package com.hello.advanced.advisor

import com.hello.advanced.common.advice.TimeAdvice
import com.hello.advanced.common.service.ServiceImpl
import com.hello.advanced.common.service.ServiceInterface
import org.junit.jupiter.api.Test
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class AdvisorTest {

    @Test
    fun advisorTest() {

        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()

    }

}
