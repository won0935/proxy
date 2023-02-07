package com.hello.advanced.pureproxy.decorator

import com.hello.advanced.pureproxy.decorator.code.DecoratorPatternClient
import com.hello.advanced.pureproxy.decorator.code.MessageDecorator
import com.hello.advanced.pureproxy.decorator.code.RealComponent
import org.junit.jupiter.api.Test

class DecoratorPatternTest {

    @Test
    fun noDecoratorTest(){

        val component = RealComponent()
        val client = DecoratorPatternClient(component)

        client.execute()
        client.execute()
        client.execute()

    }

    @Test
    fun decoratorTest(){

        val component = RealComponent()
        val decorator = MessageDecorator(component)
        val client = DecoratorPatternClient(decorator)

        client.execute()
        client.execute()
        client.execute()
    }

}
