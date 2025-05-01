package io.github.octestx.krecall.plugins.basic

import androidx.compose.runtime.Composable

/**
 * 在PluginBasicExt执行完加载流程后
 * 会尝试调用tryInit, 如果tryInit返回RequestConfigUI, 则会跳转到UI配置, tryInit返回Failed, 则会显示错误信息, 否则认为初始化成功
 */
abstract class PluginBasic(metadata: PluginMetadata): PluginBasicExt(metadata) {
    abstract fun selected()
    abstract fun unselected()
    @Composable
    abstract fun UI()

    protected abstract suspend fun tryInitInner(context: PluginContext): InitResult
    suspend fun tryInit(context: PluginContext): InitResult {
        return if (initialized.value) InitResult.Success
        else tryInitInner(context)
    }

    sealed class InitResult {
        data object Success: InitResult()
        // failed也会跳转到UI配置
        data class Failed(val exception: Exception): InitResult()
        data object RequestConfigUI: InitResult()
    }
}