package com.hello.advanced.jdkdynamic.code

import mu.KotlinLogging

class AImpl  : AInterface{

    private val log = KotlinLogging.logger {  }

    override fun call() : String{
        log.info { "a" }
        return "a"
    }
}
