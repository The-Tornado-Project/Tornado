package io.tornado.network

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.SimpleChannelInboundHandler
import io.tornado.utils.Logger

class Handler : SimpleChannelInboundHandler<ByteBuf>() {
    private val printer = Logger("Handler")

    override fun channelRead0(context: ChannelHandlerContext, msg: ByteBuf) {
        val packetData = msg.toString(Charsets.UTF_8)
        println("Packet Received: $packetData")

        val response = context.alloc().buffer()
        response.writeBytes("Hello, Client!".toByteArray(Charsets.UTF_8))
        context.writeAndFlush(response)
    }

    @Deprecated("Deprecated in Java")
    override fun exceptionCaught(context: ChannelHandlerContext, cause: Throwable) {
        printer.error("Error in handler: ${cause.message}")
        context.close()
    }
}