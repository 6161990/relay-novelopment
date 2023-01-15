package com.yoon.relayNovelopment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class RelayNovelopmentAdminApplication

fun main(args: Array<String>){
    runApplication<RelayNovelopmentAdminApplication>(*args)
}