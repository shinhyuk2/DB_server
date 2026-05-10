package com.example.dbserver.domain.likes.service;

import com.example.dbserver.domain.likes.converter.LikeConverter;
import com.example.dbserver.domain.likes.dto.LikeRequestDto;
import com.example.dbserver.domain.likes.dto.LikeResponseDto;
import com.example.dbserver.domain.likes.entity.Like;
import com.example.dbserver.domain.likes.repository.LikeRepository;
import com.example.dbserver.domain.posts.entity.Post;
import com.example.dbserver.domain.posts.repository.PostRepository;
import com.example.dbserver.domain.users.entity.User;
import com.example.dbserver.domain.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(
            LikeRepository likeRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public LikeResponseDto createLike(Long postId, LikeRequestDto requestDto) {
        Long userId = requestDto.getUserId();

        Post post = postRepository.findByPostIdAndDeletedAtIsNull(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        boolean alreadyLiked = likeRepository.existsByPostPostIdAndUserUserIdAndDeletedAtIsNull(postId, userId);

        if (alreadyLiked) {
            throw new IllegalArgumentException("이미 좋아요를 누른 게시글입니다.");
        }

        Like like = likeRepository.findByPostPostIdAndUserUserId(postId, userId)
                .orElse(null);

        if (like != null) {
            like.restore();
            post.increaseLikeCount();
            return LikeConverter.toResponseDto(like);
        }

        Like newLike = new Like(post, user);
        Like savedLike = likeRepository.save(newLike);

        post.increaseLikeCount();

        return LikeConverter.toResponseDto(savedLike);
    }

    @Transactional
    public void deleteLike(Long postId, LikeRequestDto requestDto) {
        Long userId = requestDto.getUserId();

        Like like = likeRepository.findByPostPostIdAndUserUserIdAndDeletedAtIsNull(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 찾을 수 없습니다."));

        Post post = like.getPost();

        like.delete();
        post.decreaseLikeCount();
    }
}