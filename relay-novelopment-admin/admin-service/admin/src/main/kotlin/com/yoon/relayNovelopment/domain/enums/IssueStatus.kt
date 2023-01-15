package com.yoon.relayNovelopment.domain.enums

enum class IssueStatus {
    TODO, IN_PROGRESS, RESOLVE;

    companion object {
        operator fun invoke(status: String) = valueOf(status.uppercase())
    }
}