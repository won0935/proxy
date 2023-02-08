package com.hello.advanced.config.v1_proxy.concrete_proxy

import com.hello.advanced.app.v1.OrderServiceV1
import com.hello.advanced.app.v2.OrderRepositoryV2
import com.hello.advanced.app.v2.OrderServiceV2
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus


class OrderServiceConcreteProxy(
    private val target: OrderServiceV2,
    private val orderRepositoryV2: OrderRepositoryV2,
    private val logTrace: LogTrace
) : OrderServiceV2(orderRepositoryV2) {
    override fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("Repository.begin") //로직 호출
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}
