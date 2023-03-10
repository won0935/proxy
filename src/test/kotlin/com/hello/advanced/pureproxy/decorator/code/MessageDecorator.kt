package com.hello.advanced.pureproxy.decorator.code

class MessageDecorator(
    private val component: Component
) : Component {
    override fun operation(): String {
       return "message :: " + component.operation()
    }
}
