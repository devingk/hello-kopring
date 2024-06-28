package me.mk.hello_kopring.dto

import me.mk.hello_kopring.entity.Article

data class ArticleListResponse(

    val articles: List<ArticleResponse>
) {

    companion object {
        fun from(articles: List<Article>): ArticleListResponse {

            return ArticleListResponse(
                articles.stream()
                    .map { article -> ArticleResponse.from(article) }
                    .toList()
            )
        }
    }

    data class ArticleResponse(
        val id: Long,
        val title: String,
        val contents: String
    ) {

        companion object {
            fun from(article: Article): ArticleResponse {
                return ArticleResponse(
                    id = article.id,
                    title = article.title,
                    contents = article.contents
                )
            }
        }
    }
}

