package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByEmailAddress(String emailAddress);
    Optional<User> findUserByUserNameOrEmailAddress(String username, String emailAddress);
    Boolean existsByUserName(String username);
    Boolean existsByEmailAddress(String emailAddress);
}
