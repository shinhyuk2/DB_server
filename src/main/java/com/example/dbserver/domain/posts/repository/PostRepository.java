package com.example.dbserver.domain.posts.repository;

import com.example.dbserver.domain.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDeletedAtIsNull();

    Optional<Post> findByPostIdAndDeletedAtIsNull(Long postId);
}