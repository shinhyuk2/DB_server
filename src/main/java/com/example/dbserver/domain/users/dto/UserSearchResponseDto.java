package com.example.dbserver.domain.users.dto;

import com.example.dbserver.domain.users.entity.User;
import lombok.Getter;

@Getter
public class UserSearchResponseDto {

    private final Long userId;
    private final String nickname;
    private final String profileImageUrl;

    public UserSearchResponseDto(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}