package com.back.global.app


import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AppConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    companion object {
        private lateinit var environment: Environment
        private lateinit var objectMapper: ObjectMapper
        private lateinit var siteFrontUrl: String
        private lateinit var siteBackUrl: String
        private lateinit var siteCookieDomain: String
        private lateinit var genFileDirPath: String

        @JvmStatic
        fun isProd(): Boolean = environment.matchesProfiles("prod")

        @JvmStatic
        fun isDev(): Boolean = environment.matchesProfiles("dev")

        @JvmStatic
        fun isTest(): Boolean = environment.matchesProfiles("test")

        @JvmStatic
        fun isNotProd(): Boolean = !isProd()

        @JvmStatic
        fun getObjectMapper(): ObjectMapper = objectMapper

        @JvmStatic
        fun getSiteFrontUrl(): String = siteFrontUrl

        @JvmStatic
        fun getSiteBackUrl(): String = siteBackUrl

        @JvmStatic
        fun getSiteCookieDomain(): String = siteCookieDomain

        @JvmStatic
        fun getGenFileDirPath(): String = genFileDirPath
    }

    @Autowired
    fun setEnvironment(environment: Environment) {
        Companion.environment = environment
    }

    @Autowired
    fun setObjectMapper(objectMapper: ObjectMapper) {
        Companion.objectMapper = objectMapper
    }

    @Value("\${custom.site.frontUrl}")
    fun setSiteFrontUrl(siteFrontUrl: String) {
        Companion.siteFrontUrl = siteFrontUrl
    }

    @Value("\${custom.site.backUrl}")
    fun setSiteBackUrl(siteBackUrl: String) {
        Companion.siteBackUrl = siteBackUrl
    }

    @Value("\${custom.site.cookieDomain}")
    fun setSiteCookieDomain(siteCookieDomain: String) {
        Companion.siteCookieDomain = siteCookieDomain
    }

    @Value("\${custom.genFile.dirPath}")
    fun setGenFileDirPath(genFileDirPath: String) {
        Companion.genFileDirPath = genFileDirPath
    }
}