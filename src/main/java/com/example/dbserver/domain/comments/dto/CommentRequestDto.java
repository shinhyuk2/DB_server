package com.example.dbserver.domain.comments.dto;

public class CommentRequestDto {

    private Long userId;
    private String comment;
    private Long parentCommentId;

    public CommentRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }
}