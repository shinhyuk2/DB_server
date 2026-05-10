package com.example.dbserver.domain.comments.service;

import com.example.dbserver.domain.comments.converter.CommentConverter;
import com.example.dbserver.domain.comments.dto.CommentRequestDto;
import com.example.dbserver.domain.comments.dto.CommentResponseDto;
import com.example.dbserver.domain.comments.entity.Comment;
import com.example.dbserver.domain.comments.repository.CommentRepository;
import com.example.dbserver.domain.posts.entity.Post;
import com.example.dbserver.domain.posts.repository.PostRepository;
import com.example.dbserver.domain.users.entity.User;
import com.example.dbserver.domain.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostPostIdAndDeletedAtIsNull(postId);

        return comments.stream()
                .map(CommentConverter::toResponseDto)
                .toList();
    }

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postRepository.findByPostIdAndDeletedAtIsNull(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Comment parentComment = null;

        if (requestDto.getParentCommentId() != null) {
            parentComment = commentRepository.findByCommentIdAndDeletedAtIsNull(requestDto.getParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글을 찾을 수 없습니다."));
        }

        Comment comment = new Comment(
                post,
                user,
                requestDto.getComment(),
                parentComment
        );

        Comment savedComment = commentRepository.save(comment);

        post.increaseCommentCount();

        return CommentConverter.toResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findByCommentIdAndDeletedAtIsNull(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        comment.updateComment(requestDto.getComment());

        return CommentConverter.toResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndDeletedAtIsNull(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        comment.delete();
    }
}