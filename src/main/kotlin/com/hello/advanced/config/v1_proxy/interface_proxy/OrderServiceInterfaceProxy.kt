package com.hello.advanced.config.v1_proxy.interface_proxy

import com.hello.advanced.app.v1.OrderServiceV1
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus


class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
) : OrderServiceV1 {
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
