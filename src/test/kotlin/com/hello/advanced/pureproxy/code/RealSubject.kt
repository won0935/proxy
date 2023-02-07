package com.hello.advanced.pureproxy.code

class RealSubject : Subject {

    override fun operation(): String {
        Thread.sleep(1000)
        return "data"
    }

}
