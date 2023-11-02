package com.IdentityService.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.IdentityService.entity.UserCredentials;


public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {

	Optional<UserCredentials> findByname(String username);

}
