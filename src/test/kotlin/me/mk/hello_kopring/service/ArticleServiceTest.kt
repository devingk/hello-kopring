package me.mk.hello_kopring.service

import me.mk.hello_kopring.controller.ArticleUpdateRequest
import me.mk.hello_kopring.controller.ArticleUpdateResponse
import me.mk.hello_kopring.dto.ArticleCreationResponse
import me.mk.hello_kopring.dto.ArticleListResponse
import me.mk.hello_kopring.entity.Article
import me.mk.hello_kopring.repository.ArticleRepository
import me.mk.hello_kopring.test.data.TestArticle.article
import me.mk.hello_kopring.test.data.TestArticle.articleCreationRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class ArticleServiceTest {

    @InjectMocks
    lateinit var articleService: ArticleService

    @Mock
    lateinit var articleRepository: ArticleRepository

    @Test
    @DisplayName("게시판 글 생성")
    fun createArticle() {

        //given
        val request = articleCreationRequest()
        val article = Article.from(request)
        val savedArticle = article(1L)
        given(articleRepository.save(article)).willReturn(savedArticle)

        val expectedResult = ArticleCreationResponse.from(savedArticle)

        //when
        val result = articleService.createArticle(request)

        //then
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    @DisplayName("게시판 글 목록 조회")
    fun getArticles() {

        //given
        val articles = listOf(article(1L), article(10L), article(100L))
        given(articleRepository.findAll()).willReturn(articles)

        val expectedResult = ArticleListResponse.from(articles)

        //when
        val result = articleService.getArticles()

        //then
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    @DisplayName("게시판 글 수정")
    fun updateArticle() {

        //given
        val id = 1L
        val article = article(id)
        given(articleRepository.findById(id)).willReturn(Optional.of(article))

        val request = ArticleUpdateRequest(
            title = "updated title",
            contents = "updated contents"
        )

        val updatedArticle = Article.update(id, request)
        val expectedResult = ArticleUpdateResponse.from(updatedArticle)

        //when
        val result = articleService.updateArticle(id, request)

        //then
        assertThat(result).isEqualTo(expectedResult)
    }
}