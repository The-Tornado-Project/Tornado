package io.tornado.utils

class Logger(name: String) {
    private val NAME = name

    fun info(message: Any) {
        println("[INFO] $NAME: $message")
    }

    fun debug(message: Any) {
        println("[DEBUG] $NAME: $message")
    }

    fun warn(message: Any) {
        println("[WARN] $NAME: $message")
    }

    fun error(message: Any, code: Int) {
        println("[ERROR] $NAME $code: $message")
    }
}