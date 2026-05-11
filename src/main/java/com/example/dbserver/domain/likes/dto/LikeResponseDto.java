package com.example.dbserver.domain.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LikeResponseDto {

    private Long likeId;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
}