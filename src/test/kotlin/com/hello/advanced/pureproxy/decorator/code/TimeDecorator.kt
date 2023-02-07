package com.hello.advanced.pureproxy.decorator.code

class TimeDecorator(
    private val component: Component
) : Component {
    override fun operation(): String {
       return "timed :: " + component.operation()
    }
}
