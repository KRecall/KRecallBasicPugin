package io.github.octestx.krecall.plugins.basic.model

import org.ktorm.entity.Entity

interface SearchIndex : Entity<SearchIndex> {
    companion object : Entity.Factory<SearchIndex>()
    //Auto increment id
    val id: Int
    val timestamp: Long
    val duration: Long
    //Specify the processor of the data (find the only processor that can handle the type)
    val type: String
    // Used to distinguish between multiple variants of this type of data at the same time, defaults to null, indicating no variants
    val extraLocationId: String?
    // Data keywords for search engines, split by line breaks
    val keywords: String
}