package com.maxiamikel.BirthdayNotifierAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE FUNCTION('MONTH', u.birthday) = :month AND FUNCTION('DAY', u.birthday) = :day")
    List<User> findByBirthdayToday(@Param("month") int month, @Param("day") int day);

    @Query("SELECT u FROM User u WHERE MONTH(u.birthday) = :presentMonth")
    List<User> findByBirthdayMonth(@Param("presentMonth") int presentMonth);
}
