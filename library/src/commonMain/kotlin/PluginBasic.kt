package io.github.octestx.krecall.plugins.basic

import androidx.compose.runtime.Composable

abstract class PluginBasic(metadata: PluginMetadata): PluginBasicExt(metadata) {
    abstract fun selected()
    abstract fun unselected()
    @Composable
    abstract fun UI()

    protected abstract suspend fun tryInitInner(context: PluginContext): InitResult
    suspend fun tryInit(context: PluginContext): InitResult {
        return if (initialized.value.not()) tryInitInner(context)
        else InitResult.Success
    }

    sealed class InitResult {
        data object Success: InitResult()
        // failed也会跳转到UI配置
        data class Failed(val exception: Exception): InitResult()
        data object RequestConfigUI: InitResult()
    }
}