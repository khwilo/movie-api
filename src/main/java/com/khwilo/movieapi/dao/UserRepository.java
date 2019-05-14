package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserNameOrEmailAddress(String username, String emailAddress);
    Boolean existsUserById(Long id);
    Boolean existsByUserName(String username);
    Boolean existsByEmailAddress(String emailAddress);
}
