package com.hello.advanced

import com.hello.advanced.config.DynamicProxyBasicConfig
import com.hello.advanced.config.DynamicProxyFilterConfig
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.ThreadLocalLogTrace
import com.hello.advanced.config.v3_proxyfactory.ProxyFactoryConfigV1
import com.hello.advanced.config.v3_proxyfactory.ProxyFactoryConfigV2
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


//@Import(AppV1Config::class, AppV2Config::class)
//@Import(LogTraceConfig::class, InterfaceProxyConfig::class)
//@Import(LogTraceConfig::class, ConcreteProxyConfig::class)
//@Import(LogTraceConfig::class, DynamicProxyBasicConfig::class)
//@Import(LogTraceConfig::class, DynamicProxyFilterConfig::class)
//@Import(LogTraceConfig::class, ProxyFactoryConfigV1::class)
@Import(LogTraceConfig::class, ProxyFactoryConfigV2::class)
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
