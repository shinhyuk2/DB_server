package com.example.dbserver.domain.likes.converter;

import com.example.dbserver.domain.likes.dto.LikeResponseDto;
import com.example.dbserver.domain.likes.entity.Like;

public class LikeConverter {

    private LikeConverter() {
    }

    public static LikeResponseDto toResponseDto(Like like) {
        return new LikeResponseDto(
                like.getLikeId(),
                like.getPost().getPostId(),
                like.getUser().getUserId(),
                like.getCreatedAt()
        );
    }
}