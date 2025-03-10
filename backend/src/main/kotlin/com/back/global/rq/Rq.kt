package com.back.global.rq

import com.back.global.app.AppConfig
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service


@Service
class Rq(
    private val req: HttpServletRequest,
    private val resp: HttpServletResponse
) {
    private fun cookieDomain(): String {
        val domain = AppConfig.getSiteCookieDomain()
        return if (domain == "localhost") domain else ".$domain"
    }

    fun setCookie(name: String, value: String) {
        val cookie = ResponseCookie.from(name, value)
            .path("/")
            .domain(cookieDomain())
            .sameSite("Strict")
            .secure(true)
            .httpOnly(true)
            .build()
        resp.addHeader("Set-Cookie", cookie.toString())
    }

    fun getCookieValue(name: String): String? {
        return req.cookies?.find { it.name == name }?.value
    }

    fun deleteCookie(name: String) {
        val cookie = ResponseCookie.from(name, "")
            .path("/")
            .domain("localhost")
            .sameSite("Strict")
            .secure(true)
            .httpOnly(true)
            .maxAge(0)
            .build()
        resp.addHeader("Set-Cookie", cookie.toString())
    }

    fun setHeader(name: String, value: String) {
        resp.setHeader(name, value)
    }

    fun getHeader(name: String): String? {
        return req.getHeader(name)
    }

    fun setSession(name: String, value: String) {
        req.session.setAttribute(name, value)
    }

    fun getSessionValueAsStr(name: String): String? {
        return req.session.getAttribute(name) as? String
    }
}