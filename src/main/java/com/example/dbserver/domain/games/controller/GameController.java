package com.example.dbserver.domain.games.controller;

import com.example.dbserver.domain.games.dto.GameRequestDto;
import com.example.dbserver.domain.games.dto.GameResponseDto;
import com.example.dbserver.domain.games.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public List<GameResponseDto> getGames() {
        return gameService.getGames();
    }

    @GetMapping("/{gameId}")
    public GameResponseDto getGame(@PathVariable Long gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping
    public GameResponseDto createGame(@Valid @RequestBody GameRequestDto requestDto) {
        return gameService.createGame(requestDto);
    }

    @PatchMapping("/{gameId}")
    public GameResponseDto updateGame(
            @PathVariable Long gameId,
            @Valid @RequestBody GameRequestDto requestDto
    ) {
        return gameService.updateGame(gameId, requestDto);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
    }
}