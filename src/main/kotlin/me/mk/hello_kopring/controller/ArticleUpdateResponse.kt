package me.mk.hello_kopring.controller

import me.mk.hello_kopring.entity.Article

data class ArticleUpdateResponse(
    val id: Long,
    val title: String,
    val contents: String
) {
    companion object {
        fun from(article: Article): ArticleUpdateResponse {

            return ArticleUpdateResponse(
                id = article.id,
                title = article.title,
                contents = article.contents
            )
        }
    }
}
