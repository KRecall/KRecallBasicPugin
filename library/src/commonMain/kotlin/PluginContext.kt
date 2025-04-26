package io.github.octestx.krecall.plugins.basic


abstract class PluginContext(val metadata: PluginMetadata) {
    abstract fun addMark(timestamp: Long, mark: String)
    abstract fun removeMark(timestamp: Long, mark: String)
    abstract fun listTimestampWithMark(mark: String): List<Long>
    abstract fun listTimestampWithNotMark(mark: String): List<Long>

    abstract fun addCollectingScreenStateListener(listener: (Boolean) -> Unit)
    abstract suspend fun getCollectingScreenState(): Boolean
    abstract suspend fun setCollectingScreenState(state: Boolean)
    abstract fun addProcessingDataStateListener(listener: (Boolean) -> Unit)
    abstract suspend fun getProcessingDataState(): Boolean
    abstract suspend fun setProcessingDataState(state: Boolean)

    abstract val ability: PluginAbility
}