package com.hello.advanced.pureproxy.proxy

import com.hello.advanced.pureproxy.decorator.code.DecoratorPatternClient
import com.hello.advanced.pureproxy.proxy.code.CacheProxy
import com.hello.advanced.pureproxy.proxy.code.ProxyPatternClient
import com.hello.advanced.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {

    @Test
    fun noProxyTest(){
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)

        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun cacheProxyTest(){
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)

        client.execute()
        client.execute()
        client.execute()
    }

}
