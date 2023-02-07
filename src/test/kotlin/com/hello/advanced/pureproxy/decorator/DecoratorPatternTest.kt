package com.hello.advanced.pureproxy.decorator

import com.hello.advanced.pureproxy.decorator.code.DecoratorPatternClient
import com.hello.advanced.pureproxy.decorator.code.MessageDecorator
import com.hello.advanced.pureproxy.decorator.code.RealComponent
import com.hello.advanced.pureproxy.decorator.code.TimeDecorator
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
    fun messageDecoratorTest(){

        val component = RealComponent()
        val decorator = MessageDecorator(component)
        val client = DecoratorPatternClient(decorator)

        client.execute()
        client.execute()
        client.execute()
    }

    @Test
    fun timeDecoratorTest(){

        val component = RealComponent()
        val messageDecorator = MessageDecorator(component)
        val timeDecorator = TimeDecorator(messageDecorator)
        val client = DecoratorPatternClient(timeDecorator)

        client.execute()
        client.execute()
        client.execute()
    }

}
