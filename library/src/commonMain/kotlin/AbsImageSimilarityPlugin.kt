package io.github.octestx.krecall.plugins.basic

/**
 * A parameterless constructor is required when inheriting a plugin.
 */
abstract class AbsImageSimilarityPlugin(metadata: PluginMetadata): PluginBasic(metadata) {
    /**
     * @exception Exception
     * IO
     */
    abstract suspend fun similarity(imgBytes: ByteArray, preImgBytes: ByteArray): Double
}
