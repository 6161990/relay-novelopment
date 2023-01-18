package com.yoon.relayNovelopment.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.yoon.relayNovelopment.domain.Comment
import java.time.LocalDateTime

data class CommentRequest(
    val body : String,
)

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val username: String? = null,
    val body : String ,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
)


fun Comment.toResponse() =  CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    username = username,
    body = body,
    createdAt = createdAt,
    updatedAt = updatedAt
)


