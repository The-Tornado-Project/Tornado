package io.tornado.core

import io.tornado.utils.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val logger = Logger("Main")
    logger.info("Starting daemon...")

    val server = Server(25565)
    server.start()

    launch {
        delay(10000)
        server.stop()
    }
}