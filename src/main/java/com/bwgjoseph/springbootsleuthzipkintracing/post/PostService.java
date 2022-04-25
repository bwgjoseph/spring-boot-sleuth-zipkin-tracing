package com.bwgjoseph.springbootsleuthzipkintracing.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public Post get(Integer id) {
        return this.postMapper.get(id);
    }

    @Transactional
    public Post create(Post post) {
        this.postMapper.create(post);

        return this.get(post.getId());
    }
}
