package io.github.octestx.krecall.plugins.basic

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import org.koin.java.KoinJavaComponent.get
import java.io.File

abstract class PluginBasic {
    abstract fun load()
    abstract fun selected()
    abstract fun unselected()
    @Composable
    abstract fun UI()
    protected abstract suspend fun tryInitInner(): InitResult
    suspend fun tryInit(context: IPluginContext): InitResult {
        return if (initialized.value.not()) tryInitInner()
        else InitResult.Success
    }
    abstract val initialized: StateFlow<Boolean>
    protected val pluginDir: File by lazy {
        get<IPluginContext>(IPluginContext::class.java).getPluginDir()
    }

    sealed class InitResult {
        data object Success: InitResult()
        // failed也会跳转到UI配置
        data class Failed(val exception: Exception): InitResult()
        data object RequestConfigUI: InitResult()
    }
}