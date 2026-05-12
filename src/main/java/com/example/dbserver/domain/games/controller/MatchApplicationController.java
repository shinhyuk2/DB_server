package com.example.dbserver.domain.games.controller;

import com.example.dbserver.domain.games.dto.MatchApplicationRequestDto;
import com.example.dbserver.domain.games.dto.MatchApplicationResponseDto;
import com.example.dbserver.domain.games.service.MatchApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchApplicationController {

    private final MatchApplicationService matchApplicationService;

    @PostMapping("/games/{gameId}/applications")
    public MatchApplicationResponseDto createApplication(
            @PathVariable Long gameId,
            @Valid @RequestBody MatchApplicationRequestDto requestDto
    ) {
        return matchApplicationService.createApplication(gameId, requestDto);
    }

    @GetMapping("/games/{gameId}/applications")
    public List<MatchApplicationResponseDto> getApplicationsByGame(@PathVariable Long gameId) {
        return matchApplicationService.getApplicationsByGame(gameId);
    }

    @GetMapping("/users/{userId}/applications")
    public List<MatchApplicationResponseDto> getApplicationsByUser(@PathVariable Long userId) {
        return matchApplicationService.getApplicationsByUser(userId);
    }

    @PatchMapping("/applications/{applicationId}/cancel")
    public MatchApplicationResponseDto cancelApplication(@PathVariable Long applicationId) {
        return matchApplicationService.cancelApplication(applicationId);
    }
}