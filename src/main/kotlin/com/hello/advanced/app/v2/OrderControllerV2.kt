package com.hello.advanced.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

//@RestController //Spring boot 3.0부터는 @Controller 명시해야 컨트롤러로 등록됨
class OrderControllerV2(
    private val orderServiceV2: OrderServiceV2
) {

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        orderServiceV2.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(): String {
        return "ok"
    }
}
