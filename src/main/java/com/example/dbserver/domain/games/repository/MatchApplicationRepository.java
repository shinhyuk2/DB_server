package com.example.dbserver.domain.games.repository;

import com.example.dbserver.domain.games.entity.Game;
import com.example.dbserver.domain.games.entity.mapping.MatchApplication;
import com.example.dbserver.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchApplicationRepository extends JpaRepository<MatchApplication, Long> {

    List<MatchApplication> findByGame(Game game);

    List<MatchApplication> findByUser(User user);

    Optional<MatchApplication> findByGameAndUser(Game game, User user);

    boolean existsByGameAndUser(Game game, User user);
}