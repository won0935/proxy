package com.hello.advanced.advisor

import com.hello.advanced.common.advice.TimeAdvice
import com.hello.advanced.common.service.ServiceImpl
import com.hello.advanced.common.service.ServiceInterface
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import java.lang.reflect.Method

class AdvisorTest {

    @Test
    fun advisorTest1() {

        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()

    }


    @Test
    fun advisorTest2() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(MyPointcut(), TimeAdvice())
        proxyFactory.addAdvisor(advisor)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()

    }

    class MyPointcut : Pointcut {
        override fun getClassFilter(): ClassFilter {
            return ClassFilter.TRUE
        }

        override fun getMethodMatcher(): MethodMatcher {
            return MyMethodMatcher()
        }
    }

    class MyMethodMatcher : MethodMatcher {

        private val matchName = "save"
        private val log = KotlinLogging.logger {}

        override fun matches(method: Method, targetClass: Class<*>): Boolean {
            val result = method.name == matchName
            log.info { "포인트컷 호출 $method $targetClass" }
            log.info { "포인트컷 결과 $result" }
            return result
        }

        override fun matches(method: Method, targetClass: Class<*>, vararg args: Any?): Boolean {
            return false
        }

        override fun isRuntime(): Boolean {
            return false
        }

    }

    @Test
    fun advisorTest3() {
        val target = ServiceImpl()
        val proxyFactory = ProxyFactory(target)

        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedName("save")

        val advisor = DefaultPointcutAdvisor(pointcut, TimeAdvice())
        proxyFactory.addAdvisor(advisor)

        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()

    }

}
