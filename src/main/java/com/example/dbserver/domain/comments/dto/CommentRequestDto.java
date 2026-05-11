package com.example.dbserver.domain.comments.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private Long userId;
    private String comment;
    private Long parentCommentId;
}