package com.hello.advanced.pureproxy.decorator.code

class RealComponent : Component {
    override fun operation(): String {
        Thread.sleep(1000)
        return "data"
    }
}
