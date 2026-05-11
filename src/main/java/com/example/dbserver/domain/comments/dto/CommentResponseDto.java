package com.example.dbserver.domain.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private Long postId;
    private Long userId;
    private String nickname;
    private String comment;
    private Long parentCommentId;
    private LocalDateTime createdAt;
}