package com.hello.advanced.config.v1_proxy.concrete_proxy

import com.hello.advanced.app.v2.OrderRepositoryV2
import com.hello.advanced.config.logtrace.LogTrace
import com.hello.advanced.config.logtrace.TraceStatus


class OrderRepositoryConcreteProxy(
    private val target: OrderRepositoryV2,
    private val logTrace: LogTrace
) : OrderRepositoryV2() {
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
