package com.hello.advanced

import com.hello.advanced.config.AppV1Config
import com.hello.advanced.config.AppV2Config
import com.hello.advanced.config.v1_proxy.InterfaceProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import


//@Import(AppV1Config::class, AppV2Config::class)
@Import(InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["com.hello.advanced.app"])
class AdvancedApplication

fun main(args: Array<String>) {
    runApplication<AdvancedApplication>(*args)
}
