package com.hello.advanced.config.v1_proxy.concrete_proxy

import com.hello.advanced.app.v1.OrderControllerV1
import com.hello.advanced.app.v2.OrderControllerV2
import com.hello.advanced.app.v2.OrderServiceV2
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

class OrderControllerConcreteProxy(
    private val target: OrderControllerV2,
    private val orderServiceV2: OrderServiceV2,
    private val logTrace: LogTrace
) : OrderControllerV2(orderServiceV2) {

    override fun request(@RequestParam("itemId") itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("Repository.begin") //로직 호출
            val result = target.request(itemId)
            logTrace.end(status)
            return result
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }
}
