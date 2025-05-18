package io.github.octestx.krecall.plugins.basic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PluginMetadata(
    @SerialName("plugin_id")
    val pluginId: String,

    @SerialName("main_class")
    val mainClass: String
)