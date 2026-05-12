package com.example.dbserver.domain.games.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id", nullable = false)
    private TeamInformation homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id", nullable = false)
    private TeamInformation awayTeam;

    @Column(name = "game_date", nullable = false)
    private LocalDateTime gameDate;

    @Column(name = "game_status", nullable = false, length = 20)
    private String gameStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.gameStatus == null) {
            this.gameStatus = "예정";
        }
    }

    public void updateGame(TeamInformation homeTeam, TeamInformation awayTeam, LocalDateTime gameDate, String gameStatus) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate = gameDate;
        this.gameStatus = gameStatus;
        this.updatedAt = LocalDateTime.now();
    }
}