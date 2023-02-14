package com.hello.advanced.config.v2_dynamicproxy.handler

import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
    private val target: Any,
    private val logTrace: LogTrace,
    private val filter: Array<String>
) : InvocationHandler {

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
        var status: TraceStatus? = null

        if (!PatternMatchUtils.simpleMatch(filter, method.name)) { // 스프링에서 제공하는 유틸
            return method.invoke(target, args)
        }

        try {
            val message = method.declaringClass?.simpleName + "." + method.name + "()"
            status = logTrace.begin(message) //로직 호출

            val obj = method.invoke(target, args)

            logTrace.end(status)
            return obj

        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}
