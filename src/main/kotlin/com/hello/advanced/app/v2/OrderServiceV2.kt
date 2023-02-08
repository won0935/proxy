package com.hello.advanced.app.v2

open class OrderServiceV2(
    private val orderRepositoryV2: OrderRepositoryV2
) {
    open fun orderItem(itemId: String) {
        orderRepositoryV2.save(itemId)
    }
}
