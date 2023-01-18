package com.yoon.relayNovelopment.web

import com.yoon.relayNovelopment.config.AuthUser
import com.yoon.relayNovelopment.model.CommentRequest
import com.yoon.relayNovelopment.model.CommentResponse
import com.yoon.relayNovelopment.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable("issueId") issueId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse {
        return commentService.create(issueId, authUser.userId, authUser.username, request)
    }

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable("id") id : Long,
        @RequestBody request: CommentRequest,
    ) = commentService.edit(authUser.userId, id, request)

}