package com.example.dbserver.domain.games.service;

import com.example.dbserver.domain.games.dto.GameRequestDto;
import com.example.dbserver.domain.games.dto.GameResponseDto;
import com.example.dbserver.domain.games.entity.Game;
import com.example.dbserver.domain.games.entity.TeamInformation;
import com.example.dbserver.domain.games.repository.GameRepository;
import com.example.dbserver.domain.games.repository.TeamInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;
    private final TeamInformationRepository teamInformationRepository;

    public List<GameResponseDto> getGames() {
        return gameRepository.findAll()
                .stream()
                .map(GameResponseDto::from)
                .toList();
    }

    public GameResponseDto getGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("경기를 찾을 수 없습니다."));

        return GameResponseDto.from(game);
    }

    @Transactional
    public GameResponseDto createGame(GameRequestDto requestDto) {
        TeamInformation homeTeam = teamInformationRepository.findById(requestDto.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("홈팀을 찾을 수 없습니다."));

        TeamInformation awayTeam = teamInformationRepository.findById(requestDto.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("원정팀을 찾을 수 없습니다."));

        String status = requestDto.getGameStatus() == null ? "예정" : requestDto.getGameStatus();

        Game game = Game.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .gameDate(requestDto.getGameDate())
                .gameStatus(status)
                .build();

        Game savedGame = gameRepository.save(game);

        return GameResponseDto.from(savedGame);
    }

    @Transactional
    public GameResponseDto updateGame(Long gameId, GameRequestDto requestDto) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("경기를 찾을 수 없습니다."));

        TeamInformation homeTeam = teamInformationRepository.findById(requestDto.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("홈팀을 찾을 수 없습니다."));

        TeamInformation awayTeam = teamInformationRepository.findById(requestDto.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("원정팀을 찾을 수 없습니다."));

        String status = requestDto.getGameStatus() == null ? game.getGameStatus() : requestDto.getGameStatus();

        game.updateGame(homeTeam, awayTeam, requestDto.getGameDate(), status);

        return GameResponseDto.from(game);
    }

    @Transactional
    public void deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("경기를 찾을 수 없습니다."));

        gameRepository.delete(game);
    }
}