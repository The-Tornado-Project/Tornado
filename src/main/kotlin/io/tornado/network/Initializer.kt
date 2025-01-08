package io.tornado.network

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class Initializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

        pipeline
            .addLast("encoder", Encoder())
            .addLast("decoder", Decoder())
            .addLast("handler", Handler())
    }
}