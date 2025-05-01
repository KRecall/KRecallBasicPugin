package io.github.octestx.krecall.plugins.basic


abstract class PluginContext(val metadata: PluginMetadata) {
    abstract val ability: PluginAbility
}