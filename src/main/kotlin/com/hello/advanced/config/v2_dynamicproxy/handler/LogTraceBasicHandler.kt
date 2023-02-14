package com.hello.advanced.config.v2_dynamicproxy.handler

import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
    private val target: Any,
    private val logTrace: LogTrace
) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
        var status: TraceStatus? = null
        try {
            val message = method.declaringClass?.simpleName + "." + method.name + "()"
            status = logTrace.begin(message) //로직 호출

            val obj = if (args != null) {
                method.invoke(target, args)
            } else {
                method.invoke(target)
            }

            logTrace.end(status)
            return obj

        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}
