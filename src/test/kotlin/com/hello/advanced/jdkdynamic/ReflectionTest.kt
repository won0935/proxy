package com.hello.advanced.jdkdynamic

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class ReflectionTest {

    val log = KotlinLogging.logger { }

    class Hello {
        fun callA(): String = "A"
        fun callB(): String = "B"
    }


    @Test
    fun reflection0() {

        val hello = Hello()

        log.info { "Start" }
        log.info { hello.callA() }

        log.info { hello.callB() }
        log.info { "End" }

    }

    @Test
    fun reflection1() {

        //클래스 정보
        val classHello = Hello::class.java

        val target = Hello()

        //callA메서드정보
        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)

    }

    @Test
    fun reflection2() {

        //클래스 정보
        val classHello = Hello::class.java
        val target = Hello()
        //callA메서드정보
        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)

    }

    private fun dynamicCall(method: Method, target: Any) {
        log.info { "Start" }
        log.info { method.invoke(target) }
        log.info { "End" }
    }

}

