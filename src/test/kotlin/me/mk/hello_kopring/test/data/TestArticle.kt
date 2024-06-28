package me.mk.hello_kopring.test.data

import me.mk.hello_kopring.dto.ArticleCreationRequest
import me.mk.hello_kopring.dto.ArticleCreationResponse
import me.mk.hello_kopring.entity.Article

object  TestArticle {

    fun article(): Article {
        return Article(
            title = "test article title",
            contents = "test article contents"
        )
    }

    fun article(id: Long): Article {
        return Article(
            id = id,
            title = "test article title",
            contents = "test article contents"
        )
    }

    fun articleCreationRequest(): ArticleCreationRequest {

        return ArticleCreationRequest(
            title = "test article title",
            contents = "test article contents"
        )
    }

    fun articleCreationResponse(id: Long): ArticleCreationResponse {

        return ArticleCreationResponse(
            id = id,
            title = "test article title",
            contents = "test article contents"
        )
    }
}
