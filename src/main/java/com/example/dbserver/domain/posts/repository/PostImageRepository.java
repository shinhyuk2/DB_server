package com.example.dbserver.domain.posts.repository;

import com.example.dbserver.domain.posts.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    List<PostImage> findByPostPostIdAndDeletedAtIsNull(Long postId);
}