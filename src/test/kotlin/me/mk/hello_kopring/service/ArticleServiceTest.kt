package me.mk.hello_kopring.service

import me.mk.hello_kopring.dto.ArticleCreationResponse
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

@ExtendWith(MockitoExtension::class)
class ArticleServiceTest {

    @InjectMocks
    lateinit var articleService: ArticleService

    @Mock
    lateinit var articleRepository: ArticleRepository

    @Test
    @DisplayName("아티클 글 생성")
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
}