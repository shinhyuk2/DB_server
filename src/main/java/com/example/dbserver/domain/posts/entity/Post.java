package com.example.dbserver.domain.posts.entity;

import com.example.dbserver.domain.users.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "like_count")
    private Long likeCount = 0L;

    @Column(name = "comment_count")
    private Long commentCount = 0L;

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.deletedAt = null;
        this.likeCount = 0L;
        this.commentCount = 0L;
    }

    public void increaseCommentCount() {
        if (this.commentCount == null) {
            this.commentCount = 0L;
        }

        this.commentCount++;
    }

    public void decreaseCommentCount() {
        if (this.commentCount == null || this.commentCount <= 0) {
            this.commentCount = 0L;
            return;
        }

        this.commentCount--;
    }

    public void increaseLikeCount() {
        if (this.likeCount == null) {
            this.likeCount = 0L;
        }

        this.likeCount++;
    }

    public void decreaseLikeCount() {
        if (this.likeCount == null || this.likeCount <= 0) {
            this.likeCount = 0L;
            return;
        }

        this.likeCount--;
    }

    public void updateContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}