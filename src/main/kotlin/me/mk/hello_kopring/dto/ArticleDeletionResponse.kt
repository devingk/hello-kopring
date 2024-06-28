package me.mk.hello_kopring.dto

import me.mk.hello_kopring.entity.Article

data class ArticleDeletionResponse(
    val id: Long,
    val title: String,
    val contents: String
) {
    companion object {
        fun from(article: Article): ArticleDeletionResponse {
            return ArticleDeletionResponse(
                id = article.id,
                title = article.title,
                contents = article.contents
            )
        }
    }
}
