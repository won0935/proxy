package com.hello.advanced.pureproxy.decorator.code

class DecoratorPatternClient(
    private val component: Component
) {

    fun execute(){
        component.operation()
    }

}
