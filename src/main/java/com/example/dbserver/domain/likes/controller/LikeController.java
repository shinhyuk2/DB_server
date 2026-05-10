package com.example.dbserver.domain.likes.controller;

import com.example.dbserver.domain.likes.dto.LikeRequestDto;
import com.example.dbserver.domain.likes.dto.LikeResponseDto;
import com.example.dbserver.domain.likes.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public LikeResponseDto createLike(
            @PathVariable Long postId,
            @RequestBody LikeRequestDto requestDto
    ) {
        return likeService.createLike(postId, requestDto);
    }

    @DeleteMapping
    public void deleteLike(
            @PathVariable Long postId,
            @RequestBody LikeRequestDto requestDto
    ) {
        likeService.deleteLike(postId, requestDto);
    }
}