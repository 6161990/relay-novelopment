package com.yoon.relayNovelopment.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig(
    private val authUserHandlerArgumentResolver: AuthUserHandlerArgumentResolver
) : WebMvcConfigurationSupport() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.apply {
            add(authUserHandlerArgumentResolver)
        }
    }

}

@Component
class AuthUserHandlerArgumentResolver : HandlerMethodArgumentResolver {
    /** HandlerMethodArgumentResolver  : 인증서버 JWT 로 인증할 때, 스프링 시큐리티 or 인터셉터 사용하지 않고 비교적 간단하게 인증을 구현하기 위함*/
    // 1. 컨트롤러에 인자로 들어오는 특정 객체 또는 애노테이션에 대하여 조건을 체크
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        AuthUser::class.java.isAssignableFrom(parameter.parameterType)

    // 2. 해당 객체에게 값 할당
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        // FIXME dummy
        return AuthUser(
            1L,
            "테스트"
        )
    }
}


data class AuthUser(
    val userId: Long,
    val username: String,
    val profileUrl: String? = null,
)