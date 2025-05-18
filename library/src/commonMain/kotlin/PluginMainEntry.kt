package io.github.octestx.krecall.plugins.basic

import io.github.octestx.krecall.plugins.basic.plugin.DataManager

abstract class PluginMainEntry {
    abstract fun loadDataManager(): Collection<DataManager>
}