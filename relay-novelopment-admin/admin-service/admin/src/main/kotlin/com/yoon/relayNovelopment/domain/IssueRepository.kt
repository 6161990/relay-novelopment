package com.yoon.relayNovelopment.domain

import com.yoon.relayNovelopment.domain.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {

    fun findAllByStatusOrderByCreatedAtDesc (status: IssueStatus): List<Issue>?
}