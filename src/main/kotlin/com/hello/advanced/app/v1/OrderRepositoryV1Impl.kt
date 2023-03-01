package com.hello.advanced.app.v1

open class OrderRepositoryV1Impl : OrderRepositoryV1 {
    override fun save(itemId: String) {
        require(itemId != "ex")
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        Thread.sleep(mills)
    }
}
