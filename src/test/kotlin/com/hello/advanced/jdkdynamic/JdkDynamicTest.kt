package com.hello.advanced.jdkdynamic

import com.hello.advanced.jdkdynamic.code.AImpl
import com.hello.advanced.jdkdynamic.code.AInterface
import com.hello.advanced.jdkdynamic.code.TimeInvocationHandler
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy


class JdkDynamicTest {


    @Test
    fun dynamicA() {

        val target = AImpl()
        val handler = TimeInvocationHandler(target)

        val proxy = Proxy.newProxyInstance(
            AInterface::class.java.classLoader,
            arrayOf<Class<*>>(AInterface::class.java),
            handler
        ) as AInterface

        proxy.call()
    }

}
