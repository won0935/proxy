package com.hello.advanced.config.v3_proxyfactory.advice

import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogTraceAdvice(
    private val logTrace: LogTrace
) : MethodInterceptor {

    @Throws(Throwable::class)
    override fun invoke(invocation: MethodInvocation): Any? {
        val status: TraceStatus

        val method = invocation.method
        val message = method.declaringClass.simpleName + "." +
                method.name + "()"
        status = logTrace.begin(message)

        //로직 호출
        val result = invocation.proceed()
        logTrace.end(status)
        return result

    }
}
