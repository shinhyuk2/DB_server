package com.example.dbserver.domain.games.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GameRequestDto {

    @NotNull
    private Long homeTeamId;

    @NotNull
    private Long awayTeamId;

    @NotNull
    private LocalDateTime gameDate;

    private String gameStatus;
}