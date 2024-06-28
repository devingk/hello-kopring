package me.mk.hello_kopring.service

import me.mk.hello_kopring.dto.*
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

        val article = validateArticle(id)

        val updatedArticle = Article.update(article.id, request)

        return ArticleUpdateResponse.from(updatedArticle)
    }

    private fun validateArticle(id: Long): Article = articleRepository.findById(id)
        .orElseThrow { IllegalArgumentException("The article doesn't exist.") }

    @Transactional
    fun deleteArticle(id: Long): ArticleDeletionResponse {

        val article = validateArticle(id)

        articleRepository.delete(article)

        return ArticleDeletionResponse.from(article)
    }
}
