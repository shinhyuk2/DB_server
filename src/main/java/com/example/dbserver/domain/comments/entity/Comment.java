package com.example.dbserver.domain.comments.entity;

import com.example.dbserver.domain.posts.entity.Post;
import com.example.dbserver.domain.users.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
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

    @Column(name = "created_At", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    protected Comment() {
    }

    public Comment(Post post, User user, String comment, Comment parentComment) {
        this.post = post;
        this.user = user;
        this.comment = comment;
        this.parentComment = parentComment;
        this.createdAt = LocalDateTime.now();
        this.deletedAt = null;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}