package com.example.dbserver.domain.posts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private Long userId;
    private String content;
    private List<String> imageUrls;
    private List<String> hashtags;
}