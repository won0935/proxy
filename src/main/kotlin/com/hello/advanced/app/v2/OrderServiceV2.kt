package com.hello.advanced.app.v2

class OrderServiceV2(
    private val orderRepositoryV2: OrderRepositoryV2
) {
    fun orderItem(itemId: String) {
        orderRepositoryV2.save(itemId)
    }
}
