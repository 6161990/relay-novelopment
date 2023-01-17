package com.yoon.relayNovelopment.service

import com.yoon.relayNovelopment.domain.Issue
import com.yoon.relayNovelopment.domain.IssueRepository
import com.yoon.relayNovelopment.domain.enums.IssueStatus
import com.yoon.relayNovelopment.exception.NotFoundException
import com.yoon.relayNovelopment.model.IssueRequest
import com.yoon.relayNovelopment.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(userId : Long, issueRequest : IssueRequest) : IssueResponse {

        val issue = Issue(
            userId = userId,
            summary = issueRequest.summary,
            description = issueRequest.description,
            type = issueRequest.type,
            priority = issueRequest.priority,
            status = issueRequest.status
        )

        return IssueResponse(issueRepository.save(issue))
    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus): List<IssueResponse>? =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다")
        return IssueResponse(issue)
    }

}