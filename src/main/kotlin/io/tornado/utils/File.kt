package io.tornado.utils

import java.io.File as JFile
import java.io.IOException

class File(private val file: JFile) {
    private val logger = Logger("File")
    private val name = file.name

    fun create() {
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                logger.error("Error creating file $name: ${e.message}!")
            }
        } else {
            logger.warn("File $name already exists!")
        }
    }

    fun delete() {
        if (file.exists()) {
            try {
                file.delete()
            } catch (e: IOException) {
                logger.error("Error deleting file $name: ${e.message}!")
            }
        } else {
            logger.warn("File $name does not exist!")
        }
    }

    fun read(): String? {
        return if (file.exists()) {
            try {
                file.readText()
            } catch (e: IOException) {
                logger.error("Error reading file $name: ${e.message}!")
                null
            }
        } else {
            logger.warn("File $name does not exist!")
            null
        }
    }

    fun write(content: String) {
        if (file.exists()) {
            try {
                file.writeText(content)
            } catch (e: IOException) {
                logger.error("Error writing to file $name: ${e.message}!")
            }
        } else {
            logger.warn("File $name does not exist!")
        }
    }
}
