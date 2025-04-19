package io.github.octestx.krecall.plugins.basic

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin
import java.io.File
import java.util.*

abstract class PluginBasic(val metadata: PluginMetadata): AutoCloseable {
    companion object {
        const val KOIN_INJECT_SCOPE_NAME = "PluginBasicKoinInjectScope"
    }
    private val currentTmpId: String = UUID.randomUUID().toString()
    // 声明作用域（需唯一ID）
    private val objectScope: Scope by lazy {
        getKoin().createScope(currentTmpId, named(KOIN_INJECT_SCOPE_NAME))
    }

    // 注入作用域内的依赖（延迟初始化）
    val environment: PluginEnvironment by objectScope.inject {
        parametersOf(metadata)
    }


    // 关闭作用域（在对象销毁时调用）
    override fun close() {
        objectScope.close()
    }

    abstract fun load()
    abstract fun selected()
    abstract fun unselected()
    @Composable
    abstract fun UI()
    protected abstract suspend fun tryInitInner(): InitResult
    suspend fun tryInit(context: PluginContext): InitResult {
        return if (initialized.value.not()) tryInitInner()
        else InitResult.Success
    }
    abstract val initialized: StateFlow<Boolean>
    protected val pluginDir: File by lazy {
        environment.pluginDir
    }

    sealed class InitResult {
        data object Success: InitResult()
        // failed也会跳转到UI配置
        data class Failed(val exception: Exception): InitResult()
        data object RequestConfigUI: InitResult()
    }
}