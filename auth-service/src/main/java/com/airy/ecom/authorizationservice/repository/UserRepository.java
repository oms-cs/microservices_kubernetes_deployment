package com.airy.ecom.authorizationservice.repository;

import com.airy.ecom.authorizationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
