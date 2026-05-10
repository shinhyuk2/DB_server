package com.example.dbserver.domain.posts.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_images")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long postImageId;

    // 게시글 고유 ID(post_id) - posts 테이블 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // 이미지 내용 / 이미지 URL
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // 작성 일자
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 삭제 일자
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected PostImage() {
    }

    public PostImage(Post post, String imageUrl) {
        this.post = post;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.deletedAt = null;
    }

    public Long getPostImageId() {
        return postImageId;
    }

    public Post getPost() {
        return post;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getCreatedAtAt() {
        return createdAt;
    }

    public LocalDateTime getDeletedAtAt() {
        return deletedAt;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}