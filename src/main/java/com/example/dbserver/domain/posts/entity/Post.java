package com.example.dbserver.domain.posts.entity;

import com.example.dbserver.domain.users.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    // 작성자 ID(user_id) - users 테이블 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 작성일
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 수정일
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 삭제 일자
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 게시글 내용
    @Column(name = "content")
    private String content;

    // 좋아요 수
    @Column(name = "like_count")
    private Long likeCount = 0L;

    // 댓글 수
    @Column(name = "comment_count")
    private Long commentCount = 0L;

    protected Post() {
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.deletedAt = null;
        this.likeCount = 0L;
        this.commentCount = 0L;
    }

    public Long getPostId() {
        return postId;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public String getContent() {
        return content;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void increaseCommentCount() {
        if (this.commentCount == null) {
            this.commentCount = 0L;
        }

        this.commentCount++;
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