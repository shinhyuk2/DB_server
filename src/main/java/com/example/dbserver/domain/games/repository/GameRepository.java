package com.example.dbserver.domain.games.repository;

import com.example.dbserver.domain.games.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}