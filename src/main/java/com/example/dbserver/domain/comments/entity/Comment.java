package com.example.dbserver.domain.comments.entity;

import com.example.dbserver.domain.posts.entity.Post;
import com.example.dbserver.domain.users.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    public Comment(Post post, User user, String comment, Comment parentComment) {
        this.post = post;
        this.user = user;
        this.comment = comment;
        this.parentComment = parentComment;
        this.createdAt = LocalDateTime.now();
        this.deletedAt = null;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}