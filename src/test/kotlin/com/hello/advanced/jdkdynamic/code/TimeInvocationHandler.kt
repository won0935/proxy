package com.hello.advanced.jdkdynamic.code

import mu.KotlinLogging
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any,
) : InvocationHandler {
    private val log = KotlinLogging.logger {}

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        log.info { "Time Proxy 실행" }
        val startTime = System.currentTimeMillis()

        val obj = if (args != null) {
            method?.invoke(target, args)
        } else {
            method?.invoke(target)
        }
        val endTime = System.currentTimeMillis()
        log.info { "Time Proxy 종료 resultTime = ${endTime - startTime}" }
        return obj
    }
}
