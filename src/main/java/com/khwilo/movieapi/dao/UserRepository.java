package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUserName(String userName);
}
