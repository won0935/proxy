package com.hello.advanced.config.v1_proxy.interface_proxy

import com.hello.advanced.app.v1.OrderRepositoryV1
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus


class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
) : OrderRepositoryV1 {
    override fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("Repository.begin") //로직 호출
            target.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}
