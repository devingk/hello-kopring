package me.mk.hello_kopring.dto

import jakarta.validation.constraints.NotBlank
import me.mk.hello_kopring.constant.ErrorMessages.Companion.NOT_BLANK

data class ArticleUpdateRequest(

    @field:NotBlank(message = "The title $NOT_BLANK")
    val title: String,

    @field:NotBlank(message = "The contents $NOT_BLANK")
    val contents: String
)
