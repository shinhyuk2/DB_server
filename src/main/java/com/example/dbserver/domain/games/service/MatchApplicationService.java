package com.example.dbserver.domain.games.service;

import com.example.dbserver.domain.games.dto.MatchApplicationRequestDto;
import com.example.dbserver.domain.games.dto.MatchApplicationResponseDto;
import com.example.dbserver.domain.games.entity.Game;
import com.example.dbserver.domain.games.entity.mapping.MatchApplication;
import com.example.dbserver.domain.games.repository.GameRepository;
import com.example.dbserver.domain.games.repository.MatchApplicationRepository;
import com.example.dbserver.domain.users.entity.User;
import com.example.dbserver.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchApplicationService {

    private final MatchApplicationRepository matchApplicationRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Transactional
    public MatchApplicationResponseDto createApplication(Long gameId, MatchApplicationRequestDto requestDto) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("경기를 찾을 수 없습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        MatchApplication existingApplication = matchApplicationRepository.findByGameAndUser(game, user)
                .orElse(null);

        if (existingApplication != null) {
            if ("신청완료".equals(existingApplication.getApplicationStatus())) {
                throw new IllegalArgumentException("이미 신청한 경기입니다.");
            }

            if ("신청취소".equals(existingApplication.getApplicationStatus())) {
                existingApplication.reApply(
                        requestDto.getPreferredAge(),
                        requestDto.getPreferredMbti(),
                        requestDto.getPreferredGender(),
                        requestDto.getPreferredHeadcount()
                );

                return MatchApplicationResponseDto.from(existingApplication);
            }
        }

        MatchApplication application = MatchApplication.builder()
                .game(game)
                .user(user)
                .applicationStatus("신청완료")
                .preferredAge(requestDto.getPreferredAge())
                .preferredMbti(requestDto.getPreferredMbti())
                .preferredGender(requestDto.getPreferredGender())
                .preferredHeadcount(requestDto.getPreferredHeadcount())
                .build();

        MatchApplication savedApplication = matchApplicationRepository.save(application);

        return MatchApplicationResponseDto.from(savedApplication);
    }

    public List<MatchApplicationResponseDto> getApplicationsByGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("경기를 찾을 수 없습니다."));

        return matchApplicationRepository.findByGame(game)
                .stream()
                .map(MatchApplicationResponseDto::from)
                .toList();
    }

    public List<MatchApplicationResponseDto> getApplicationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return matchApplicationRepository.findByUser(user)
                .stream()
                .map(MatchApplicationResponseDto::from)
                .toList();
    }

    @Transactional
    public MatchApplicationResponseDto cancelApplication(Long applicationId) {
        MatchApplication application = matchApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("신청 내역을 찾을 수 없습니다."));

        application.cancel();

        return MatchApplicationResponseDto.from(application);
    }
}