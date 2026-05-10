package com.example.dbserver.domain.posts.repository;

import com.example.dbserver.domain.posts.entity.mapping.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

    List<PostHashtag> findByPostPostId(Long postId);
}