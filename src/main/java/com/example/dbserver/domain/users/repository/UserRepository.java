package com.example.dbserver.domain.users.repository;

import com.example.dbserver.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNicknameContaining(String nickname);
}