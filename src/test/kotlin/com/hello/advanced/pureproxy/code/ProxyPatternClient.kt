package com.hello.advanced.pureproxy.code

class ProxyPatternClient(
    private val subject: Subject
) {

    fun execute(){
        subject.operation()
    }

}
