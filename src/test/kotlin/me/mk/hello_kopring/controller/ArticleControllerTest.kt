package me.mk.hello_kopring.controller

import me.mk.hello_kopring.dto.*
import me.mk.hello_kopring.entity.Article
import me.mk.hello_kopring.service.ArticleService
import me.mk.hello_kopring.test.data.TestArticle
import me.mk.hello_kopring.test.data.TestArticle.article
import me.mk.hello_kopring.test.data.TestArticle.articleCreationRequest
import me.mk.hello_kopring.test.data.TestArticle.articleCreationResponse
import me.mk.hello_kopring.test.data.TestContext.objectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ArticleController::class])
class ArticleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var articleService: ArticleService

    @Test
    @DisplayName("게시판 글 생성")
    fun createArticle() {

        //given
        val path = "/articles"

        val request = articleCreationRequest()
        val response = articleCreationResponse(1L)
        given(articleService.createArticle(request)).willReturn(response)

        //when
        val resultActions = mockMvc.perform(
            post(path)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())

        //then
        resultActions.andExpectAll(
            status().isOk,
            content().json(objectMapper.writeValueAsString(response))
        )
    }

    @Test
    @DisplayName("게시판 글 생성 - 예외1(title이 blank)")
    fun createArticle_fail_01() {

        //given
        val path = "/articles"

        val request = ArticleCreationRequest(
            title = "     ",
            contents = "test contents"
        )

        //when
        val resultActions = mockMvc.perform(
            post(path)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())

        //then
        resultActions.andExpectAll(
            status().isBadRequest
        )
    }

    @Test
    @DisplayName("글 목록 조회")
    fun getArticles() {

        //given
        val path = "/articles"

        val articles = listOf(article(1L), article(2), article(10))
        val expectedResult = ArticleListResponse.from(articles)
        given(articleService.getArticles()).willReturn(expectedResult)

        //when
        val resultActions = mockMvc.perform(
            get(path)
        )
            .andDo(print())

        //then
        resultActions.andExpectAll(
            status().isOk,
            content().json(objectMapper.writeValueAsString(expectedResult))
        )
    }

    @Test
    @DisplayName("게시판 글 수정")
    fun updateArticle() {

        //given
        val path = "/articles/{id}"

        val id = 1L
        val request = ArticleUpdateRequest(
            title = "updated article",
            contents = "test contents"
        )
        val updatedArticle = Article.update(id, request)
        val expectedResult = ArticleUpdateResponse.from(updatedArticle)
        given(articleService.updateArticle(id, request)).willReturn(expectedResult)

        //when
        val resultActions = mockMvc.perform(
            put(path, id)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())

        //then
        resultActions.andExpectAll(
            status().isOk,
            content().json(objectMapper.writeValueAsString(expectedResult))
        )
    }

    @Test
    @DisplayName("게시판 글 삭제")
    fun deleteArticle() {

        //given
        val path = "/articles/{id}"

        val id = 1L
        val article = article(id)
        val expectedResult = ArticleDeletionResponse.from(article)
        given(articleService.deleteArticle(id)).willReturn(expectedResult)

        //when
        val resultActions = mockMvc.perform(
            delete(path, id)
        )
            .andDo(print())

        //then
        resultActions.andExpectAll(
            status().isOk,
            content().json(objectMapper.writeValueAsString(expectedResult))
        )
    }
}