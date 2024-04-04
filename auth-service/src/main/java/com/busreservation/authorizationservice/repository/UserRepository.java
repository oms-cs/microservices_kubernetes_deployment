package com.busreservation.authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreservation.authorizationservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
