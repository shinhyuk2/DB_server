package com.example.dbserver.domain.posts.repository;

import com.example.dbserver.domain.posts.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDeletedAtIsNull();

    Optional<Post> findByPostIdAndDeletedAtIsNull(Long postId);

    @Query("""
            select p
            from Post p
            where p.deletedAt is null
            order by p.createdAt desc
            """)
    List<Post> findAllOrderByCreatedAt(Pageable pageable);

    @Query("""
            select p
            from Post p
            left join PostLike l on l.post = p and l.deletedAt is null
            where p.deletedAt is null
            group by p
            order by count(l) desc, p.createdAt desc
            """)
    List<Post> findAllOrderByLikeCount(Pageable pageable);

    @Query("""
            select p
            from Post p
            join PostHashtag ph on ph.post = p
            join ph.hashtag h
            where p.deletedAt is null
            and h.tagName = :hashtag
            order by p.createdAt desc
            """)
    List<Post> findByHashtagOrderByCreatedAt(
            @Param("hashtag") String hashtag,
            Pageable pageable
    );

    @Query("""
            select p
            from Post p
            join PostHashtag ph on ph.post = p
            join ph.hashtag h
            left join PostLike l on l.post = p and l.deletedAt is null
            where p.deletedAt is null
            and h.tagName = :hashtag
            group by p
            order by count(l) desc, p.createdAt desc
            """)
    List<Post> findByHashtagOrderByLikeCount(
            @Param("hashtag") String hashtag,
            Pageable pageable
    );
}