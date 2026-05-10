package com.example.dbserver.domain.comments.converter;

import com.example.dbserver.domain.comments.dto.CommentResponseDto;
import com.example.dbserver.domain.comments.entity.Comment;

public class CommentConverter {

    private CommentConverter() {
    }

    public static CommentResponseDto toResponseDto(Comment comment) {
        Long parentCommentId = null;

        if (comment.getParentComment() != null) {
            parentCommentId = comment.getParentComment().getCommentId();
        }

        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getPost().getPostId(),
                comment.getUser().getUserId(),
                comment.getUser().getNickname(),
                comment.getComment(),
                parentCommentId,
                comment.getCreatedAt()
        );
    }
}