package com.hello.advanced.common.service

import mu.KotlinLogging

open class ConcreteService {
    val log = KotlinLogging.logger {}

    open fun call() {
        log.info("ConcreteService 호출")
    }

}
