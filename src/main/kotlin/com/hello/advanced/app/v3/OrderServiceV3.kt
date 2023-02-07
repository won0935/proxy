package com.hello.advanced.app.v3

import org.springframework.stereotype.Service

@Service
class OrderServiceV3(
    private val orderRepositoryV3: OrderRepositoryV3
) {
    fun orderItem(itemId: String) {
        orderRepositoryV3.save(itemId)
    }
}
