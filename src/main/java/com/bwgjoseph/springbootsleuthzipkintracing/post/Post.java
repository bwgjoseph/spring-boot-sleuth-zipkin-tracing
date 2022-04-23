package com.bwgjoseph.springbootsleuthzipkintracing.post;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
	private Integer id;
    private String title;
    private String body;
	private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
