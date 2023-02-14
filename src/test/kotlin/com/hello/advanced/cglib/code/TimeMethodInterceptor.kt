package com.hello.advanced.cglib.code

import mu.KotlinLogging
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

class TimeMethodInterceptor(
    private val target: Any,
) : MethodInterceptor {
    private val log = KotlinLogging.logger {}

    override fun intercept(obj: Any?, method: Method?, args: Array<out Any>?, proxy: MethodProxy?): Any? {
        log.info { "Time Proxy 실행" }
        val startTime = System.currentTimeMillis()

        val obj = proxy?.invoke(target, args)

        val endTime = System.currentTimeMillis()
        log.info { "Time Proxy 종료 resultTime = ${endTime - startTime}" }
        return obj
    }
}
