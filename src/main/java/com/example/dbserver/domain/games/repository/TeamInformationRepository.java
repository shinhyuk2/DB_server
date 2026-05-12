package com.example.dbserver.domain.games.repository;

import com.example.dbserver.domain.games.entity.TeamInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamInformationRepository extends JpaRepository<TeamInformation, Long> {
}