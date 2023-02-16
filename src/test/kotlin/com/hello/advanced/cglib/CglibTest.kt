package com.hello.advanced.cglib

import com.hello.advanced.cglib.code.TimeMethodInterceptor
import com.hello.advanced.common.service.ConcreteService
import org.junit.jupiter.api.Test
import org.springframework.cglib.proxy.Enhancer

class CglibTest {

    @Test
    fun cglib(){
        val target = ConcreteService()

        val enhancer = Enhancer()
        enhancer.setSuperclass(ConcreteService::class.java)
        enhancer.setCallback(TimeMethodInterceptor(target))
        val proxy = enhancer.create() as ConcreteService


    }

}
