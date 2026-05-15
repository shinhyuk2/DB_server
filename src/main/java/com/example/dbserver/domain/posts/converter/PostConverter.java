package com.example.dbserver.domain.posts.converter;

import com.example.dbserver.domain.posts.dto.PostResponseDto;
import com.example.dbserver.domain.posts.entity.Post;

import java.util.List;

public class PostConverter {

    private PostConverter() {
    }

    public static PostResponseDto toResponseDto(
            Post post,
            List<String> imageUrls,
            List<String> hashtags
    ) {
        return new PostResponseDto(
                post.getPostId(),
                post.getUser().getUserId(),
                post.getUser().getNickname(),
                post.getUser().getProfileImageUrl(),
                post.getContent(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                imageUrls,
                hashtags
        );
    }
}