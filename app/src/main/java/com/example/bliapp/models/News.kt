package com.example.bliapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("by")
    val author: String? = null,
    @SerialName("descendants")
    val descendants: Int? = null,
    @SerialName("kids")
    val kids: List<Int>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("score")
    val score: Int? = null,
    @SerialName("time")
    val publishedTime: Long? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null
)