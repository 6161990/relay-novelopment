package com.yoon.relayNovelopment.domain

import com.yoon.relayNovelopment.domain.enums.IssuePriority
import com.yoon.relayNovelopment.domain.enums.IssueStatus
import com.yoon.relayNovelopment.domain.enums.IssueType
import javax.persistence.*


@Table
@Entity
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var userId: Long,

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type : IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority : IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status : IssueStatus

    ) : BaseEntity()