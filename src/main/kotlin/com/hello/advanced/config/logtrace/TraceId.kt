package com.hello.advanced.config.logtrace


import java.util.*

data class TraceId(
    val id: String = createId(),
    val level: Long = 0L,
) {

    fun isFirstLevel(): Boolean {
        return level == 0L
    }

    companion object {

        private fun createId(): String {
            return UUID.randomUUID().toString().substring(0, 8)
        }

        fun createNextTraceId(traceId: TraceId): TraceId {
            return TraceId(
                createId(),
                traceId.level + 1
            )
        }

        fun createPreviousTraceId(traceId: TraceId): TraceId {
            return TraceId(
                createId(),
                traceId.level - 1
            )
        }
    }
}
