package com.example.dbserver.domain.users.service;

import com.example.dbserver.domain.users.dto.UserSearchResponseDto;
import com.example.dbserver.domain.users.entity.User;
import com.example.dbserver.domain.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserSearchResponseDto> searchUsers(String nickname) {
        List<User> users = userRepository.findByNicknameContaining(nickname);

        return users.stream()
                .map(UserSearchResponseDto::new)
                .toList();
    }
}