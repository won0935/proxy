package com.hello.advanced

import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.ThreadLocalLogTrace
import com.hello.advanced.config.v1_proxy.InterfaceProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


//@Import(AppV1Config::class, AppV2Config::class)
@Import(LogTraceConfig::class, InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["com.hello.advanced.app"])
class AdvancedApplication

fun main(args: Array<String>) {


    runApplication<AdvancedApplication>(*args)
}

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }
}
