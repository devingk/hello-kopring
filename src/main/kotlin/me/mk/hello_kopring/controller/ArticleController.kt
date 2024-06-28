package me.mk.hello_kopring.controller

import me.mk.hello_kopring.dto.*
import me.mk.hello_kopring.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @GetMapping
    fun getArticles(): ResponseEntity<ArticleListResponse> {

        return ResponseEntity.ok(articleService.getArticles())
    }

    @PutMapping("/{id}")
    fun updateArticle(@PathVariable id: Long, @Validated @RequestBody request: ArticleUpdateRequest)
            : ResponseEntity<ArticleUpdateResponse> {

        return ResponseEntity.ok(articleService.updateArticle(id, request))
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<ArticleDeletionResponse> {

        return ResponseEntity.ok(articleService.deleteArticle(id))
    }
}