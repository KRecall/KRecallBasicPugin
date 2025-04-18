package io.github.octestx.krecall.plugins.basic

import java.io.File

abstract class IPluginContext(val metadata: PluginMetadata) {
    abstract fun getPluginDir(): File
    abstract fun getPluginScreenDir(): File
    abstract fun addMark(timestamp: Long, mark: String)
    abstract fun removeMark(timestamp: Long, mark: String)
    abstract fun listTimestampWithMark(mark: String): List<Long>
    abstract fun listTimestampWithNotMark(mark: String): List<Long>
}