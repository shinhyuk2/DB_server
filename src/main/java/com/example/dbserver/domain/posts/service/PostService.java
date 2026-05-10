package com.example.dbserver.domain.posts.service;

import com.example.dbserver.domain.hashtags.entity.Hashtag;
import com.example.dbserver.domain.hashtags.repository.HashtagRepository;
import com.example.dbserver.domain.posts.converter.PostConverter;
import com.example.dbserver.domain.posts.dto.PostRequestDto;
import com.example.dbserver.domain.posts.dto.PostResponseDto;
import com.example.dbserver.domain.posts.entity.Post;
import com.example.dbserver.domain.posts.entity.PostImage;
import com.example.dbserver.domain.posts.entity.mapping.PostHashtag;
import com.example.dbserver.domain.posts.repository.PostHashtagRepository;
import com.example.dbserver.domain.posts.repository.PostImageRepository;
import com.example.dbserver.domain.posts.repository.PostRepository;
import com.example.dbserver.domain.users.entity.User;
import com.example.dbserver.domain.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostImageRepository postImageRepository;
    private final PostHashtagRepository postHashtagRepository;
    private final HashtagRepository hashtagRepository;

    public PostService(
            PostRepository postRepository,
            UserRepository userRepository,
            PostImageRepository postImageRepository,
            PostHashtagRepository postHashtagRepository,
            HashtagRepository hashtagRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postImageRepository = postImageRepository;
        this.postHashtagRepository = postHashtagRepository;
        this.hashtagRepository = hashtagRepository;
    }

    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findByDeletedAtIsNull();

        return posts.stream()
                .map(this::toPostResponseDto)
                .toList();
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findByPostIdAndDeletedAtIsNull(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        return toPostResponseDto(post);
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Post post = new Post(user, requestDto.getContent());
        Post savedPost = postRepository.save(post);

        savePostImages(savedPost, requestDto.getImageUrls());
        savePostHashtags(savedPost, requestDto.getHashtags());

        return toPostResponseDto(savedPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndDeletedAtIsNull(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.updateContent(requestDto.getContent());

        updatePostImages(post, requestDto.getImageUrls());
        updatePostHashtags(post, requestDto.getHashtags());

        return toPostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostIdAndDeletedAtIsNull(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.delete();
    }

    private void savePostImages(Post post, List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            return;
        }

        for (String imageUrl : imageUrls) {
            PostImage postImage = new PostImage(post, imageUrl);
            postImageRepository.save(postImage);
        }
    }

    private void savePostHashtags(Post post, List<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return;
        }

        for (String tagName : tagNames) {
            Hashtag hashtag = hashtagRepository.findByTagName(tagName)
                    .orElseGet(() -> hashtagRepository.save(new Hashtag(tagName)));

            PostHashtag postHashtag = new PostHashtag(post, hashtag);
            postHashtagRepository.save(postHashtag);
        }
    }

    private void updatePostImages(Post post, List<String> imageUrls) {
        List<PostImage> oldImages = postImageRepository.findByPostPostIdAndDeletedAtIsNull(post.getPostId());

        for (PostImage oldImage : oldImages) {
            oldImage.delete();
        }

        savePostImages(post, imageUrls);
    }

    private void updatePostHashtags(Post post, List<String> tagNames) {
        List<PostHashtag> oldPostHashtags = postHashtagRepository.findByPostPostId(post.getPostId());

        postHashtagRepository.deleteAll(oldPostHashtags);
        postHashtagRepository.flush();

        savePostHashtags(post, tagNames);
    }

    private PostResponseDto toPostResponseDto(Post post) {
        List<String> imageUrls = postImageRepository.findByPostPostIdAndDeletedAtIsNull(post.getPostId())
                .stream()
                .map(PostImage::getImageUrl)
                .toList();

        List<String> hashtags = postHashtagRepository.findByPostPostId(post.getPostId())
                .stream()
                .map(PostHashtag::getHashtag)
                .map(Hashtag::getTagName)
                .toList();

        return PostConverter.toResponseDto(post, imageUrls, hashtags);
    }
}