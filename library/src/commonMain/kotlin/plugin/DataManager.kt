package io.github.octestx.krecall.plugins.basic.plugin

import androidx.compose.runtime.Composable
import io.github.octestx.krecall.plugins.basic.logic.DataProcessScope
import io.github.octestx.krecall.plugins.basic.logic.ProcessPipe
import io.github.octestx.krecall.plugins.basic.model.SearchIndex
import io.github.octestx.krecall.plugins.basic.model.WaitingProcess

abstract class DataManager {
    abstract fun register()
    abstract fun ProcessPipe.capture()

    @Composable
    abstract fun ShaderSearchCard()

    @Composable
    abstract fun ShaderInfoFolded()

    @Composable
    abstract fun ShaderInfoFull()

    interface ProcessPipeRegister {
        fun registerReceiver(type: String, receiver: DataProcessScope.(WaitingProcess) -> Unit)
        fun <R> registerDataRover(type: String, provider: (SearchIndex) -> R)
        fun registerDataJsonRover(type: String, provider: (SearchIndex) -> String)
    }
}