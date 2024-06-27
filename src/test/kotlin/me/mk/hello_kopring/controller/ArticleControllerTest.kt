package me.mk.hello_kopring.controller

import me.mk.hello_kopring.service.ArticleService
import me.mk.hello_kopring.test.data.TestArticle
import me.mk.hello_kopring.test.data.TestContext
import me.mk.hello_kopring.test.data.TestContext.objectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
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

        val request = TestArticle.articleCreationRequest()
        val response = TestArticle.articleCreationResponse(1L)
        given(articleService.createArticle(request)).willReturn(response)

        //when
        val resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post(path)
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
}