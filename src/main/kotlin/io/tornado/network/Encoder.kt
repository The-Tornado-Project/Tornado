package io.tornado.network

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import io.tornado.utils.VarInt
import io.tornado.utils.writeVarInt

class Encoder : MessageToByteEncoder<ByteBuf>() {
    override fun encode(ctx: ChannelHandlerContext, msg: ByteBuf, out: ByteBuf) {
        val length = msg.readableBytes()
        val varint =  VarInt(length)
        writeVarInt(varint) { out.writeByte(it.toInt()) }
        out.writeBytes(msg)
    }
}