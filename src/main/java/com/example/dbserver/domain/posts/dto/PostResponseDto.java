package com.example.dbserver.domain.posts.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDto {

    private Long postId;
    private Long userId;
    private String nickname;
    private String content;
    private Long likeCount;
    private Long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> imageUrls;
    private List<String> hashtags;

    public PostResponseDto(
            Long postId,
            Long userId,
            String nickname,
            String content,
            Long likeCount,
            Long commentCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<String> imageUrls,
            List<String> hashtags
    ) {
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageUrls = imageUrls;
        this.hashtags = hashtags;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}