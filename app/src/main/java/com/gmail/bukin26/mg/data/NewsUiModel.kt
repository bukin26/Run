package com.gmail.bukin26.mg.data

data class NewsUiModel(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val title: String,
    val url: String,
    val content: String,
    val sourceName: String,
)

fun NewsDTO.toUiModel(): NewsUiModel {
    return NewsUiModel(
        publishedAt = this.publishedAt ?: "",
        author = this.author ?: "",
        urlToImage = this.urlToImage ?: "",
        description = this.description ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        content = this.content ?: "",
        sourceName = this.source?.name ?: ""
    )
}
