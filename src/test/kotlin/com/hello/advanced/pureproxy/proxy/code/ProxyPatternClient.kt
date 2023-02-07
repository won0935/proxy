package com.hello.advanced.pureproxy.proxy.code

class ProxyPatternClient(
    private val subject: Subject
) {

    fun execute(){
        subject.operation()
    }

}
