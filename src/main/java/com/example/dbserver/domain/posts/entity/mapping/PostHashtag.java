package com.example.dbserver.domain.posts.entity.mapping;

import com.example.dbserver.domain.hashtags.entity.Hashtag;
import com.example.dbserver.domain.posts.entity.Post;
import jakarta.persistence.*;

@Entity
@Table(name = "post_hashtags")
public class PostHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_hashtag_id")
    private Long postHashtagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", nullable = false)
    private Hashtag hashtag;

    protected PostHashtag() {
    }

    public PostHashtag(Post post, Hashtag hashtag) {
        this.post = post;
        this.hashtag = hashtag;
    }

    public Long getPostHashtagId() {
        return postHashtagId;
    }

    public Post getPost() {
        return post;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }
}