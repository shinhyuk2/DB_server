package com.example.dbserver.domain.hashtags.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hashtags")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long hashtagId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    protected Hashtag() {
    }

    public Hashtag(String tagName) {
        this.tagName = tagName;
    }

    public Long getHashtagId() {
        return hashtagId;
    }

    public String getTagName() {
        return tagName;
    }
}