package com.hello.advanced.app.v2

class OrderRepositoryV2 {
    fun save(itemId: String) {
        require(itemId != "ex")
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        Thread.sleep(mills)
    }
}
