package com.yoon.relayNovelopment.service

import com.yoon.relayNovelopment.domain.Comment
import com.yoon.relayNovelopment.domain.CommentRepository
import com.yoon.relayNovelopment.domain.IssueRepository
import com.yoon.relayNovelopment.exception.NotFoundException
import com.yoon.relayNovelopment.model.CommentRequest
import com.yoon.relayNovelopment.model.CommentResponse
import com.yoon.relayNovelopment.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(issueId: Long, userId: Long, username: String, commentRequest: CommentRequest): CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")
        val comment = Comment(
                issue = issue,
                userId = userId,
                username = username,
                body = commentRequest.body
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(userId: Long, id: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        commentRepository.findByIdAndUserId(id, userId)?.let {
            comment -> issue.comments.remove(comment)
        }
    }
}