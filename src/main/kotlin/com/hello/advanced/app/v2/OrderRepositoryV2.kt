package com.hello.advanced.app.v2

open class OrderRepositoryV2 {
    open fun save(itemId: String) {
        require(itemId != "ex")
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        Thread.sleep(mills)
    }
}
