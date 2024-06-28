package me.mk.hello_kopring.repository

import me.mk.hello_kopring.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {

}
