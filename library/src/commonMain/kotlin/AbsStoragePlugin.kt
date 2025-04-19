package io.github.octestx.krecall.plugins.basic

import org.koin.java.KoinJavaComponent.get
import java.io.File
import java.io.OutputStream

/**
 * A parameterless constructor is required when inheriting a plugin.
 */
abstract class AbsStoragePlugin(): PluginBasic() {
    abstract suspend fun requireImageOutputStream(timestamp: Long): OutputStream
    abstract suspend fun requireImageFileBitItNotExits(timestamp: Long): File
    abstract suspend fun requireAudioOutputStream(timestamp: Long): OutputStream
    abstract suspend fun processed(timestamp: Long)
    /**
     * @exception NoSuchFileException
     */
    abstract suspend fun getScreenData(timestamp: Long): Result<ByteArray>
    protected val imageDir: File by lazy {
        File(environment.pluginDir, "ScreenImages").apply {
            if (!exists()) mkdirs()
        }
    }
    protected val audioDir: File by lazy {
        File(environment.pluginDir, "Audios").apply {
            if (!exists()) mkdirs()
        }
    }
    protected fun mark(timestamp: Long, mark: String) = get<PluginContext>(PluginContext::class.java).addMark(timestamp, mark)
    protected fun listTimestampWithMark(mark: String): List<Long> = get<PluginContext>(PluginContext::class.java).listTimestampWithMark(mark)
    protected fun listTimestampWithNotMark(mark: String): List<Long> = get<PluginContext>(PluginContext::class.java).listTimestampWithNotMark(mark)
}