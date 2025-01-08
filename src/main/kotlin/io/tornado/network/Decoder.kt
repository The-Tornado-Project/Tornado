package io.tornado.network

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import kotlin.experimental.and

class Decoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        input.markReaderIndex()
        var bytesRead = 0
        var value = 0

        while (true) {
            if (!input.isReadable) {
                input.resetReaderIndex()
                return
            }

            val byte = input.readByte()
            value = value or ((byte and 0x7F).toInt() shl (bytesRead * 7))

            bytesRead++
            if (bytesRead > 5) throw RuntimeException("VarInt too big")

            if ((byte and 0x80.toByte()) == 0.toByte()) break
        }

        if (input.readableBytes() < value) {
            input.resetReaderIndex()
            return
        }

        out.add(input.readBytes(value))
    }
}