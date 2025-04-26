package io.github.octestx.krecall.plugins.basic


abstract class PluginContext(val metadata: PluginMetadata) {
    abstract fun addMark(timestamp: Long, mark: String)
    abstract fun removeMark(timestamp: Long, mark: String)
    abstract fun listTimestampWithMark(mark: String): List<Long>
    abstract fun listTimestampWithNotMark(mark: String): List<Long>
    abstract fun imageSimilarity(img1: ByteArray, img2: ByteArray): Double
}