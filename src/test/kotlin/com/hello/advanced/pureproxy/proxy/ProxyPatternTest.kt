package com.hello.advanced.pureproxy.proxy

import com.hello.advanced.pureproxy.proxy.code.CacheProxy
import com.hello.advanced.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun noProxyTest(){
        val realSubject = RealSubject()
        realSubject.operation()
        realSubject.operation()
        realSubject.operation()
    }

    @Test
    fun cacheProxyTest(){
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        cacheProxy.operation()
        cacheProxy.operation()
        cacheProxy.operation()
    }

}
