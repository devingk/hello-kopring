package me.mk.hello_kopring.repository

import me.mk.hello_kopring.test.data.TestArticle.article
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Test
    @DisplayName("아티클 글 저장")
    fun createArticle() {

        //given
        val article = article()
        val expectedResult = article(1L)

        //when
        val result = articleRepository.save(article)

        //then
        assertThat(result).isEqualTo(expectedResult)

    }
}