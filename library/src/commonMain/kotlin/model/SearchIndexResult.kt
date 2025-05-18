package io.github.octestx.krecall.plugins.basic.model

data class SearchIndexResult(
    val main: SearchIndex,
    val extra: List<SearchIndex>
)
