package com.example.dbserver.domain.games.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchApplicationRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Integer preferredAge;

    @NotNull
    private String preferredMbti;

    @NotNull
    private String preferredGender;

    @NotNull
    private Integer preferredHeadcount;
}