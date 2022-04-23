package com.bwgjoseph.springbootsleuthzipkintracing.post;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @GetMapping
    public Post get() {
        return Post.builder()
            .id(1)
            .title("uan")
            .body("is fat")
            .createdAt(LocalDateTime.now())
            .createdBy("por")
            .updatedAt(LocalDateTime.now())
            .updatedBy("por")
            .build();
    }
}
