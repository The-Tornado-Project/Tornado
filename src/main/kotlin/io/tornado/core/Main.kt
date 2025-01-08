package io.tornado.core

import io.tornado.utils.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

fun main(): Unit = runBlocking {
    val logger = Logger("Main")
    logger.info("Starting daemon...")

    val file = File("server.properties.json")
    val properties = Config(file, mapOf("port" to 25565, "protocol" to 769, "version" to "1.21.4")).load()

    val port = properties["port"]!!.toString().toDouble().toInt()
    val protocol = properties["protocol"]!!.toString().toDouble().toInt()
    val version = properties["version"]!!.toString()

    val server = Server(port, protocol, version)
    server.start()

    launch {
        delay(60000)
        server.stop()
    }
}