package me.mk.hello_kopring.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import me.mk.hello_kopring.controller.ArticleUpdateRequest
import me.mk.hello_kopring.dto.ArticleCreationRequest

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @Column(columnDefinition = "text")
    val contents: String
) {

    companion object {
        fun from(request: ArticleCreationRequest): Article {

            return Article(
                title = request.title,
                contents = request.contents
            )
        }

        fun update(id: Long, request: ArticleUpdateRequest): Article {
            return Article(
                id = id,
                title = request.title,
                contents = request.contents
            )
        }
    }
}

