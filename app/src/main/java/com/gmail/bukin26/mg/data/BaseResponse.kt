package com.gmail.bukin26.mg.data

import com.google.gson.annotations.SerializedName

class BaseResponse<R>(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    private val articles: List<R>?
) {
    fun data(): List<R>? = articles
}