package com.hello.advanced.config.logtrace


data class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)
