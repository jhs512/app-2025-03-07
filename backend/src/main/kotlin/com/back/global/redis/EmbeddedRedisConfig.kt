package com.back.global.redis

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import java.io.IOException

@Profile("!prod")
@Configuration
class EmbeddedRedisConfig {
    @Value("\${spring.data.redis.port}")
    private val redisPort = 0
    private lateinit var redisServer: RedisServer

    @PostConstruct
    @Throws(IOException::class)
    fun startRedis() {
        val port = redisPort
        redisServer = RedisServer(port)
        redisServer.start()
    }

    @PreDestroy
    @Throws(IOException::class)
    fun stopRedis() {
        redisServer.stop()
    }
}