package io.github.octestx.krecall.plugins.basic

/**
 * A parameterless constructor is required when inheriting a plugin.
 */
abstract class AbsScreenStorageFilterPlugin(metadata: PluginMetadata): PluginBasic(metadata) {
    /**
     * after the first storage, when the image recognised, return true can keep image, return false can discard image and only save recognised data
     * @exception Exception
     * @exception UnsupportedOperationException
     * IO
     */
    abstract suspend fun filter(imgBytes: ByteArray, preImgBytes: ByteArray): Boolean
}