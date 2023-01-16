package com.yoon.relayNovelopment.web

import com.yoon.relayNovelopment.config.AuthUser
import com.yoon.relayNovelopment.domain.enums.IssueStatus
import com.yoon.relayNovelopment.model.IssueRequest
import com.yoon.relayNovelopment.service.IssueService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService
){

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody issueRequest: IssueRequest
    ) = issueService.create(authUser.userId, issueRequest)

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus
    ) = issueService.getAll(status)


}