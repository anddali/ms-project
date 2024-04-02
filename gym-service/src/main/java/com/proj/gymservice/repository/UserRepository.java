package com.proj.gymservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.gymservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhone(String phone);
}
