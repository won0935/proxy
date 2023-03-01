package com.hello.advanced.config.v5_autoproxy

import com.hello.advanced.LogTraceConfig
import com.hello.advanced.config.AppV1Config
import com.hello.advanced.config.AppV2Config
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class, LogTraceConfig::class)
class AutoProxyConfig {

//    @Bean
//    fun advisor1(logTrace: LogTrace): Advisor {
//
//        val pointcut = NameMatchMethodPointcut()
//        pointcut.setMappedNames("request*", "order*", "save*")
//
//        val advice = LogTraceAdvice(logTrace)
//        return DefaultPointcutAdvisor(pointcut, advice)
//    }

//    @Bean
//    fun advisor2(logTrace: LogTrace): Advisor {
//
//        val pointcut = AspectJExpressionPointcut()
//        pointcut.expression = "execution(* com.hello.advanced.app..*(..))"
//
//        val advice = LogTraceAdvice(logTrace)
//        return DefaultPointcutAdvisor(pointcut, advice)
//    }

    @Bean
    fun advisor3(logTrace: LogTrace): Advisor {

        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* com.hello.advanced.app..*(..)) && !execution(* com.hello.advanced.app..noLog(..))"

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}
