package me.mk.hello_kopring.service

import me.mk.hello_kopring.controller.ArticleUpdateRequest
import me.mk.hello_kopring.controller.ArticleUpdateResponse
import me.mk.hello_kopring.dto.ArticleCreationRequest
import me.mk.hello_kopring.dto.ArticleCreationResponse
import me.mk.hello_kopring.dto.ArticleListResponse
import me.mk.hello_kopring.entity.Article
import me.mk.hello_kopring.repository.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleService(
    val articleRepository: ArticleRepository

) {

    @Transactional
    fun createArticle(request: ArticleCreationRequest): ArticleCreationResponse {

        val article = articleRepository.save(Article.from(request))

        return ArticleCreationResponse.from(article)
    }

    fun getArticles(): ArticleListResponse {

        val articles = articleRepository.findAll()

        return ArticleListResponse.from(articles)
    }

    @Transactional
    fun updateArticle(id: Long, request: ArticleUpdateRequest): ArticleUpdateResponse {

        val article = articleRepository.findById(id)
            .orElseThrow { IllegalArgumentException("The article doesn't exist.") }

        val updatedArticle = Article.update(article.id, request)

        return ArticleUpdateResponse.from(updatedArticle)
    }
}
