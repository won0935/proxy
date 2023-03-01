package com.hello.advanced.config.v6_aop

import com.hello.advanced.LogTraceConfig
import com.hello.advanced.config.AppV1Config
import com.hello.advanced.config.AppV2Config
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.v6_aop.aspect.LogTraceAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class, LogTraceConfig::class)
class AopConfig {

    @Bean
    fun logTraceAspect(logTrace: LogTrace): LogTraceAspect {
        return LogTraceAspect(logTrace)
    }

}
