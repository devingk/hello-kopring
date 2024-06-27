package me.mk.hello_kopring.dto

import me.mk.hello_kopring.entity.Article

data class ArticleCreationResponse(
    val id: Long,
    val title: String,
    val contents: String
) {
    companion object {
        fun from(article: Article): ArticleCreationResponse {

            return ArticleCreationResponse(
                id = article.id,
                title = article.title,
                contents = article.contents
            )
        }
    }
}
