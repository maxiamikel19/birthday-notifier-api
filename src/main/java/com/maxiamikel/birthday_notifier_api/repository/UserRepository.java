package com.maxiamikel.birthday_notifier_api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maxiamikel.birthday_notifier_api.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE EXTRACT(MONTH FROM u.birthDate) = :month ORDER BY EXTRACT(DAY FROM u.birthDate)")
    Page<User> findUsersByBirthMonth(@Param("month") int month, Pageable pageable);
}
