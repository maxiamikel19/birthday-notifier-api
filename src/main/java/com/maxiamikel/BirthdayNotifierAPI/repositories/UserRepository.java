package com.maxiamikel.BirthdayNotifierAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxiamikel.BirthdayNotifierAPI.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
