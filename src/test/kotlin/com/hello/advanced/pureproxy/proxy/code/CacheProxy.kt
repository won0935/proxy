package com.hello.advanced.pureproxy.proxy.code

class CacheProxy(
    private val target: Subject
) : Subject {

    private var cacheValue : String = ""

    override fun operation(): String {

        if(cacheValue.isEmpty())
            cacheValue = target.operation()

        return cacheValue
    }
}
