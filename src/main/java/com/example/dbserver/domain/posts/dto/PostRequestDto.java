package com.example.dbserver.domain.posts.dto;

import java.util.List;

public class PostRequestDto {

    private Long userId;
    private String content;
    private List<String> imageUrls;
    private List<String> hashtags;

    public PostRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}