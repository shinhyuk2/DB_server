package com.example.dbserver.domain.games.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team_information")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TeamInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false, length = 50)
    private String teamName;

    @Column(name = "region", nullable = false, length = 50)
    private String region;

    @Column(name = "stadium", nullable = false, length = 100)
    private String stadium;

    @Column(name = "team_emblem", nullable = false)
    private String teamEmblem;
}