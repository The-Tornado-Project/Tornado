package io.tornado.core

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File as JFile

class Config(
    private val file: JFile,
    private val default: Map<String, Any>
) {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val typeToken = object : TypeToken<Map<String, Any>>() {}

    init {
        if (!file.exists()) {
            save(default)
        }
    }

    private fun save(data: Map<String, Any>) {
        file.writeText(gson.toJson(data, typeToken.type))
    }

    fun load(): Map<String, Any> {
        return if (file.exists()) {
            gson.fromJson(file.readText(), typeToken.type) ?: default
        } else {
            default
        }
    }
}
