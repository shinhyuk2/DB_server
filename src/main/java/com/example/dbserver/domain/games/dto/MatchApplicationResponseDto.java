package com.example.dbserver.domain.games.dto;

import com.example.dbserver.domain.games.entity.mapping.MatchApplication;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MatchApplicationResponseDto {

    private Long applicationId;

    private Long gameId;

    private Long userId;
    private String nickname;

    private String applicationStatus;

    private LocalDateTime appliedAt;
    private LocalDateTime canceledAt;

    private Integer preferredAge;
    private String preferredMbti;
    private String preferredGender;
    private Integer preferredHeadcount;

    public static MatchApplicationResponseDto from(MatchApplication application) {
        return MatchApplicationResponseDto.builder()
                .applicationId(application.getApplicationId())
                .gameId(application.getGame().getGameId())
                .userId(application.getUser().getUserId())
                .nickname(application.getUser().getNickname())
                .applicationStatus(application.getApplicationStatus())
                .appliedAt(application.getAppliedAt())
                .canceledAt(application.getCanceledAt())
                .preferredAge(application.getPreferredAge())
                .preferredMbti(application.getPreferredMbti())
                .preferredGender(application.getPreferredGender())
                .preferredHeadcount(application.getPreferredHeadcount())
                .build();
    }
}