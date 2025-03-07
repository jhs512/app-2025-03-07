package com.back.domain.post.post.controller

import com.back.domain.post.post.entity.Post
import com.back.domain.post.post.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/posts")
class ApiV1PostController(
    private val postService: PostService
) {
    @GetMapping
    fun getItems(): List<Post> {
        return postService.findAll()
    }
}