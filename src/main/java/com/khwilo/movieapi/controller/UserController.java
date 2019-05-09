package com.khwilo.movieapi.controller;

import com.khwilo.movieapi.model.User;
import com.khwilo.movieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("You have registered successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>("User has been removed successfully", HttpStatus.OK);
    }
}
