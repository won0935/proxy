package com.hello.advanced.app.v3

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3 {
    fun save(itemId: String) {
        require(itemId != "ex")
        sleep(1000)
    }

    private fun sleep(mills: Long) {
        Thread.sleep(mills)
    }
}
