package com.hello.advanced.config.logtrace

interface LogTrace {

    fun begin(message: String): TraceStatus

    fun end(traceStatus: TraceStatus)

    fun exception(traceStatus: TraceStatus, exception: Exception)

}
