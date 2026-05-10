package com.example.dbserver.domain.hashtags.repository;

import com.example.dbserver.domain.hashtags.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByTagName(String tagName);
}