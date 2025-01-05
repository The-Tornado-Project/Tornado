package io.tornado.utils

class Elapse {
    private var startTime: Long = 0
    private var endTime: Long = 0

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun stop() {
        endTime = System.currentTimeMillis()
    }

    fun elapsedMillis(): Long {
        return endTime - startTime
    }

    fun elapsedSeconds(): Long {
        return elapsedMillis() / 1000
    }
}
