package me.mk.hello_kopring.service

import me.mk.hello_kopring.dto.ArticleCreationRequest
import me.mk.hello_kopring.dto.ArticleCreationResponse
import me.mk.hello_kopring.entity.Article
import me.mk.hello_kopring.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleService(
    val articleRepository: ArticleRepository

) {
    fun createArticle(request: ArticleCreationRequest): ArticleCreationResponse {

        val article = articleRepository.save(Article.from(request))

        return ArticleCreationResponse.from(article)
    }
}
