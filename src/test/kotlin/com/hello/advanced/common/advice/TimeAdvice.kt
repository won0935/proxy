package com.hello.advanced.common.advice

import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation


class TimeAdvice : MethodInterceptor {

    private val log = KotlinLogging.logger {}

    override fun invoke(invocation: MethodInvocation): Any? {
        log.info { "Time Proxy 실행" }
        val startTime = System.currentTimeMillis()

        val obj = invocation.proceed() // 프록시 팩토리에서 이미 정보를 세팅하고 있음

        val endTime = System.currentTimeMillis()
        log.info { "Time Proxy 종료 resultTime = ${endTime - startTime}" }
        return obj
    }
}
