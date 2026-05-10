package com.example.dbserver.domain.comments.repository;

import com.example.dbserver.domain.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostPostIdAndDeletedAtIsNull(Long postId);

    Optional<Comment> findByCommentIdAndDeletedAtIsNull(Long commentId);
}