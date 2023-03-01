package com.hello.advanced.config.v6_aop.aspect

import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect


@Aspect
class LogTraceAspect(private val logTrace: LogTrace) {

    @Around("execution(* com.hello.advanced.app..*(..)) && !execution(* com.hello.advanced.app..noLog(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any {

        val status: TraceStatus
        val message = joinPoint.signature.toShortString()
        status = logTrace.begin(message)

        //로직 호출
        val result = joinPoint.proceed()
        logTrace.end(status)
        return result
    }

}
