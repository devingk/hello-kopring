package me.mk.hello_kopring.controller

import me.mk.hello_kopring.dto.ArticleCreationRequest
import me.mk.hello_kopring.dto.ArticleCreationResponse
import me.mk.hello_kopring.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService
) {

    @PostMapping
    fun createArticle(@Validated @RequestBody request: ArticleCreationRequest)
            : ResponseEntity<ArticleCreationResponse> {

        return ResponseEntity.ok(articleService.createArticle(request))
    }
}