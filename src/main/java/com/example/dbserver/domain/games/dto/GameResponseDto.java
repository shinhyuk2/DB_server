package com.example.dbserver.domain.games.dto;

import com.example.dbserver.domain.games.entity.Game;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GameResponseDto {

    private Long gameId;

    private Long homeTeamId;
    private String homeTeamName;
    private String homeTeamEmblem;

    private Long awayTeamId;
    private String awayTeamName;
    private String awayTeamEmblem;

    private LocalDateTime gameDate;
    private String gameStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GameResponseDto from(Game game) {
        return GameResponseDto.builder()
                .gameId(game.getGameId())
                .homeTeamId(game.getHomeTeam().getTeamId())
                .homeTeamName(game.getHomeTeam().getTeamName())
                .homeTeamEmblem(game.getHomeTeam().getTeamEmblem())
                .awayTeamId(game.getAwayTeam().getTeamId())
                .awayTeamName(game.getAwayTeam().getTeamName())
                .awayTeamEmblem(game.getAwayTeam().getTeamEmblem())
                .gameDate(game.getGameDate())
                .gameStatus(game.getGameStatus())
                .createdAt(game.getCreatedAt())
                .updatedAt(game.getUpdatedAt())
                .build();
    }
}