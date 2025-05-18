package io.github.octestx.krecall.plugins.basic.logic

interface DataProcessScope {
    /**
     * 添加数据
     * 记得判断每个keyword是否有换行，防止污染
     */
    fun add(duration: Long, keywords: List<String>, fromId: String? = null)
}