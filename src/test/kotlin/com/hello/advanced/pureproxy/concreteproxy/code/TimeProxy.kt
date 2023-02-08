package com.hello.advanced.pureproxy.concreteproxy.code

import java.time.LocalDateTime

class TimeProxy(
    private val concreteLogic: ConcreteLogic
) : ConcreteLogic() {
    override fun operation(): String {
        return LocalDateTime.now().toString() + "::" + concreteLogic.operation()
    }
}
