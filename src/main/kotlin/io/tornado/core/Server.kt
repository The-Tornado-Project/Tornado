package io.tornado.core

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.tornado.network.Initializer
import io.tornado.utils.Elapse
import io.tornado.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicBoolean

class Server(port: Int, protocol: Int, version: String) {
    private val logger = Logger("Server")

    private val PORT: Int = port
    private val PROTOCOL: Int = protocol
    private val VERSION: String = version

    // Netty bootstrap
    private val bootstrap = ServerBootstrap()

    // Netty NIO groups
    private val workerGroup = NioEventLoopGroup()
    private val bossGroup = NioEventLoopGroup()

    // False means the server is not running
    private val isRunning = AtomicBoolean(false)

    // Elapse
    private val startElapse = Elapse()
    private val stopElapse = Elapse()

    init {
        logger.info("Initialized server on protocol version $PROTOCOL and Minecraft version $VERSION.")
    }

    fun start(): Unit = runBlocking {
        // Checking if the server is not running
        if (isRunning.compareAndSet(false, true)) {
            logger.info("Starting server on port $PORT...")
            startElapse.start()

            // Using Dispatchers.IO for network operations
            launch(Dispatchers.IO) {
                try {
                    bootstrap
                        .group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel::class.java)
                        .childHandler(Initializer())
                        .bind(PORT)
                        .sync()


                    startElapse.stop()
                    logger.info("Server started in ${startElapse.elapsedSeconds()}s ${startElapse.elapsedMillis()}ms")
                } catch (e: Exception) {
                    logger.error("Error starting server: ${e.message}")
                }
            }
        } else {
            logger.warn("The server is already running!")
        }
    }

    fun stop(): Unit = runBlocking {
        if (isRunning.compareAndSet(true, false)) {
            logger.info("Stopping server...")
            stopElapse.start()

            launch(Dispatchers.Default) {
                workerGroup.shutdownGracefully()
                bossGroup.shutdownGracefully()

                stopElapse.stop()
                logger.info("Server stopped in ${stopElapse.elapsedSeconds()}s ${stopElapse.elapsedMillis()}ms")
            }
        } else {
            logger.warn("The server is not running!")
        }
    }
}