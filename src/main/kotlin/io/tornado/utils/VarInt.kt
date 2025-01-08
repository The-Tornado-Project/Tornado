package io.tornado.utils

import kotlin.experimental.and

@JvmInline
value class VarInt(private val value: Int) {
    fun toInt(): Int = value
}

internal inline fun writeVarInt(varint: VarInt, writeByte: (Byte) -> Unit) {
    var value = varint.toInt()

    while (true) {
        if ((value and 0xFFFFFF80.toInt()) == 0) {
            writeByte(value.toByte())
            return
        }

        writeByte(((value and 0x7F) or 0x80).toByte())
        value = value ushr 7
    }
}

internal inline fun readVarInt(readByte: () -> Byte): VarInt {
    val logger = Logger("VarInt")
    var offset = 0
    var value = 0L
    var byte: Byte

    do {
        if (offset == 35) logger.error("VarInt too long")

        byte = readByte()
        value = value or ((byte.toLong() and 0x7FL) shl offset)

        offset += 7
    } while ((byte and 0x80.toByte()) != 0.toByte())

    return VarInt(value.toInt())
}