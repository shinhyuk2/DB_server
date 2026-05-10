package com.example.dbserver.domain.comments.dto;

import java.time.LocalDateTime;

public class CommentResponseDto {

    private Long commentId;
    private Long postId;
    private Long userId;
    private String nickname;
    private String comment;
    private Long parentCommentId;
    private LocalDateTime createdAt;

    public CommentResponseDto(
            Long commentId,
            Long postId,
            Long userId,
            String nickname,
            String comment,
            Long parentCommentId,
            LocalDateTime createdAt
    ) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.nickname = nickname;
        this.comment = comment;
        this.parentCommentId = parentCommentId;
        this.createdAt = createdAt;
    }

    public Long getCommentId() {
        return commentId;
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

    public String getComment() {
        return comment;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}