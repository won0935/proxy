package com.hello.advanced.config.v4_postprocessor

import com.hello.advanced.LogTraceConfig
import com.hello.advanced.config.AppV1Config
import com.hello.advanced.config.AppV2Config
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v3_proxyfactory.advice.LogTraceAdvice
import com.hello.advanced.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(AppV1Config::class, AppV2Config::class, LogTraceConfig::class)
class BeanPostProcessorConfig {

    @Bean
    fun logTracePostProcessor(logTrace: LogTrace): PackageLogTracePostProcessor {
        return PackageLogTracePostProcessor("com.hello.advanced.app", getAdvisor(logTrace))
    }

    fun getAdvisor(logTrace: LogTrace): Advisor {

        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")

        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)

    }

}
