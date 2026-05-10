package com.example.dbserver.domain.likes.repository;

import com.example.dbserver.domain.likes.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostPostIdAndUserUserIdAndDeletedAtIsNull(Long postId, Long userId);

    Optional<Like> findByPostPostIdAndUserUserIdAndDeletedAtIsNull(Long postId, Long userId);

    Optional<Like> findByPostPostIdAndUserUserId(Long postId, Long userId);
}