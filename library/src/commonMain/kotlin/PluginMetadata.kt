package io.github.octestx.krecall.plugins.basic

import io.github.octestx.basic.multiplatform.common.utils.OS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PluginMetadata(
    @SerialName("plugin_id")
    val pluginId: String,

    @SerialName("support_platform")
    val supportPlatform: Set<OS.OperatingSystem>,

    @SerialName("support_ui")
    val supportUI: Boolean,

    @SerialName("main_class")
    val mainClass: String
)