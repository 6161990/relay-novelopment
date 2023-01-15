package com.yoon.relayNovelopment.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


/** JPA 에서 모든 엔티티에 공통으로 들어갈만한 속성을 부모 엔티티에 넣고 공용적으로 사용한다. */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class) // 엔티티에 특정한 이벤트가 발생하면 콜백함수 등을 처리함. @CreatedDate @LastModifiedDate 등을 감지하여 값을 세팅해줌. @EnableJpaAuditing 를 스프링 애플리케이션 메인에 달아주어야함.
abstract class BaseEntity(

    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
)
