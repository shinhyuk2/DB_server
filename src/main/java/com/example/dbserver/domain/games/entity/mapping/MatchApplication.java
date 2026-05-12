package com.example.dbserver.domain.games.entity.mapping;

import com.example.dbserver.domain.games.entity.Game;
import com.example.dbserver.domain.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_applications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MatchApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "application_status", nullable = false, length = 20)
    private String applicationStatus;

    @Column(name = "applied_at", nullable = false)
    private LocalDateTime appliedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @Column(name = "preferred_age", nullable = false)
    private Integer preferredAge;

    @Column(name = "preferred_mbti", nullable = false, length = 10)
    private String preferredMbti;

    @Column(name = "preferred_gender", nullable = false, length = 10)
    private String preferredGender;

    @Column(name = "preferred_headcount", nullable = false)
    private Integer preferredHeadcount;

    @PrePersist
    public void prePersist() {
        this.appliedAt = LocalDateTime.now();

        if (this.applicationStatus == null) {
            this.applicationStatus = "신청완료";        }
    }

    public void cancel() {
        this.applicationStatus = "신청취소";
        this.canceledAt = LocalDateTime.now();
    }

    public void reApply(Integer preferredAge, String preferredMbti, String preferredGender, Integer preferredHeadcount) {
        this.applicationStatus = "신청완료";
        this.canceledAt = null;
        this.appliedAt = LocalDateTime.now();
        this.preferredAge = preferredAge;
        this.preferredMbti = preferredMbti;
        this.preferredGender = preferredGender;
        this.preferredHeadcount = preferredHeadcount;
    }
}