package com.example.dbserver.domain.users.repository;

import com.example.dbserver.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}