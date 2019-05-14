package com.khwilo.movieapi.service;

import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.exception.AppException;
import com.khwilo.movieapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(
                user -> users.add(user)
        );
        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new AppException("User with id '" + id + "' not found!")
        );
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}