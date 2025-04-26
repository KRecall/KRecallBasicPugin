package io.github.octestx.krecall.plugins.basic

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import io.github.octestx.basic.multiplatform.ui.ui.utils.ToastModel

abstract class PluginAbility {
    abstract fun setDrawerUI(content: @Composable ColumnScope.() -> Unit)
    abstract fun addMainTab(tabName: String, tabContent: @Composable () -> Unit)
    abstract fun addSettingTab(tabName: String, tabContent: @Composable () -> Unit)
    abstract suspend fun sendToast(toast: ToastModel, dismissListener: () -> Unit = {})
    suspend fun sendToast(text: String, type: ToastModel.Type = ToastModel.Type.Normal) {
        sendToast(ToastModel(text, type = type)) {}
    }
    abstract suspend fun sendMessage(text: String)
}