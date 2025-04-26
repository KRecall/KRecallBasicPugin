package io.github.octestx.krecall.plugins.basic

import kotlinx.coroutines.flow.StateFlow
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin
import java.io.File
import java.util.*

abstract class PluginBasicExt(val metadata: PluginMetadata): AutoCloseable {
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
    abstract val initialized: StateFlow<Boolean>
    protected val pluginDir: File by lazy {
        environment.pluginDir
    }
}