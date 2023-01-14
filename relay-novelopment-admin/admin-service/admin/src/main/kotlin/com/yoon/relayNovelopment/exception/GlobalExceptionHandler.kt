package com.yoon.relayNovelopment.exception

import com.auth0.jwt.exceptions.TokenExpiredException
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    val logger = KotlinLogging.logger{}

    @ExceptionHandler
    fun handleServerException(ex: ServerException) : ErrorResponse {
        logger.error { ex.message }
        return ErrorResponse(ex.code, ex.message)
    }

    @ExceptionHandler
    fun handleTokenExpiredException(ex: TokenExpiredException) : ErrorResponse {
        logger.error { ex.message }
        return ErrorResponse(401, "Token Expired Error")
    }

    @ExceptionHandler
    fun handleException(ex: Exception) : ErrorResponse {
        logger.error { ex.message }
        return ErrorResponse(500, "Internal Server Error") // 간혹 여기를 ex.message 그대로 내려주는 경우가 있는데, 그럼 stackTraceMessage 가 노출된다. 노출되면 안되는 정보가 있을수도 있기 때문에 보안취약점이된다.
    }

}