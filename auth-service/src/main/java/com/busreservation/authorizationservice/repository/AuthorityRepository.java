package com.busreservation.authorizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busreservation.authorizationservice.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
