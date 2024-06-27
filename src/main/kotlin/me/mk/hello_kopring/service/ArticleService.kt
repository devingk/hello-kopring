package me.mk.hello_kopring.service

import me.mk.hello_kopring.dto.ArticleCreationRequest
import me.mk.hello_kopring.dto.ArticleCreationResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleService(
) {
    fun createArticle(request: ArticleCreationRequest): ArticleCreationResponse {
        TODO("Not yet implemented")
    }
}
