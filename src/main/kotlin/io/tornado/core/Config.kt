package io.tornado.core

import io.tornado.utils.File
import io.tornado.utils.Logger
import java.io.File as JFile

class Config(file: File, default: Map<String, Any>?) {
    private val logger = Logger("Config")

    private val DEFAULT = default ?: emptyMap()
}