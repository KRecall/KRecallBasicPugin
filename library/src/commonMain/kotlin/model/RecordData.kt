package io.github.octestx.krecall.plugins.basic.model

import io.github.octestx.krecall.plugins.basic.WindowInfo

data class RecordData(
    val timestamp: Long,
    val marks: List<String>,
    val ocr: String,
    val data: String,
    val status: Int,
    val error: String?,
    val windowInfo: WindowInfo
)
