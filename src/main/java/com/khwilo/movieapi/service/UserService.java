package com.khwilo.movieapi.service;

import com.khwilo.movieapi.dao.UserRepository;
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
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(
                user -> users.add(user)
        );
        return users;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}