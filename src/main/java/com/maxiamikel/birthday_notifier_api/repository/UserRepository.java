package com.maxiamikel.birthday_notifier_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxiamikel.birthday_notifier_api.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
}
