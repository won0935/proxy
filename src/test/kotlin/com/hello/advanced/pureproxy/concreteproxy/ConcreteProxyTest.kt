package com.hello.advanced.pureproxy.concreteproxy

import com.hello.advanced.pureproxy.concreteproxy.code.ConcreteClient
import com.hello.advanced.pureproxy.concreteproxy.code.ConcreteLogic
import com.hello.advanced.pureproxy.concreteproxy.code.TimeProxy
import org.junit.jupiter.api.Test

class ConcreteProxyTest {

    @Test
    fun noProxy(){
        val concreteLogic = ConcreteLogic()
        val concreteClient = ConcreteClient(concreteLogic)
        concreteClient.execute()
    }

    @Test
    fun withProxy(){
        val concreteLogic = ConcreteLogic()
        val timeProxy = TimeProxy(concreteLogic)
        val concreteClient = ConcreteClient(timeProxy)
        concreteClient.execute()
    }

}
