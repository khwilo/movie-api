package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByEmailAddress(String emailAddress);
    Optional<User> findUserByUserNameOrEmailAddress(String username, String emailAddress);
    Boolean existsByUserName(String username);
    Boolean existsByEmailAddress(String emailAddress);
}
