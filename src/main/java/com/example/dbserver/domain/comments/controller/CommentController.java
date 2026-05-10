package com.example.dbserver.domain.comments.controller;

import com.example.dbserver.domain.comments.dto.CommentRequestDto;
import com.example.dbserver.domain.comments.dto.CommentResponseDto;
import com.example.dbserver.domain.comments.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponseDto> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public CommentResponseDto createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto
    ) {
        return commentService.createComment(postId, requestDto);
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto
    ) {
        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
    }
}