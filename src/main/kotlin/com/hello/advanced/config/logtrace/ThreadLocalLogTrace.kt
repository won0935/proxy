package com.hello.advanced.config.logtrace


import mu.KotlinLogging

class ThreadLocalLogTrace(
    private val traceIdHolder: ThreadLocal<TraceId> = ThreadLocal<TraceId>(), //쓰레드로컬 적용
) : LogTrace {
    private val log = KotlinLogging.logger {}

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startMills = System.currentTimeMillis()
        log.info(
            "[{}] {}{}", traceId!!.id, addSpace(
                START_PREFIX,
                traceId.level
            ), message
        )

        return TraceStatus(
            traceId = traceId, startTimeMs = startMills, message = message
        )
    }

    private fun syncTraceId() {
        if (traceIdHolder.get() == null) {
            traceIdHolder.set(TraceId())
        } else {
            traceIdHolder.set(TraceId.createNextTraceId(traceIdHolder.get()))
        }
    }


    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, exception: Exception) {
        complete(status, exception)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val (id, level) = status.traceId
        if (e == null) {
            log.info(
                "[{}] {}{} time={}ms", id,
                addSpace(COMPLETE_PREFIX, level), status.message,
                resultTimeMs
            )
        } else {
            log.info(
                "[{}] {}{} time={}ms ex={}", id,
                addSpace(EX_PREFIX, level), status.message, resultTimeMs,
                e.toString()
            )
        }

        releaseTraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder.get().isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(TraceId.createPreviousTraceId(traceIdHolder.get()))
        }
    }

    private fun addSpace(prefix: String, level: Long): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }
}
