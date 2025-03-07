package com.back.domain.home.home.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class HomeControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DisplayName("메인")
    fun t1() {
        val resultActions = mvc
            .perform(
                get("/")
            )
            .andDo(print())

        resultActions
            .andExpect(handler().handlerType(HomeController::class.java))
            .andExpect(handler().methodName("main"))
            .andExpect(status().isOk())
    }
}