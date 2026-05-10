package com.example.dbserver.domain.likes.dto;

import java.time.LocalDateTime;

public class LikeResponseDto {

    private Long likeId;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;

    public LikeResponseDto(Long likeId, Long postId, Long userId, LocalDateTime createdAt) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getLikeId() {
        return likeId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}