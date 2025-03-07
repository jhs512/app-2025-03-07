package com.back.domain.post.post.controller

import com.back.domain.post.post.service.PostService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class ApiV1PostControllerTest {
    @Autowired
    private lateinit var postService: PostService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @DisplayName("다건 조회")
    fun t1() {
        val resultActions = mvc
            .perform(
                MockMvcRequestBuilders.get("/api/v1/posts")
            )
            .andDo(MockMvcResultHandlers.print())

        val posts = postService
            .findAll()

        resultActions
            .andExpect(MockMvcResultMatchers.handler().handlerType(ApiV1PostController::class.java))
            .andExpect(MockMvcResultMatchers.handler().methodName("getItems"))
            .andExpect(MockMvcResultMatchers.status().isOk())

        for (i in posts.indices) {
            val post = posts[i]
            resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$[$i].id").value(post.id))
                .andExpect(MockMvcResultMatchers.jsonPath("$[$i].title").value(post.title))
        }
    }
}