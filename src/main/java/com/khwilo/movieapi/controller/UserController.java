package com.khwilo.movieapi.controller;

import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.payload.ApiResponse;
import com.khwilo.movieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        if (userService.getAllUsers().isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "There exists no users!"),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        Long userId = Long.parseLong(id);
        if (!userRepository.existsUserById(userId)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "User with id '" + id + "' not found!"),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable("id") String id) {
        Long userId = Long.parseLong(id);
        if (!userRepository.existsUserById(userId)) {
            return new ResponseEntity<>(
                    new ApiResponse(
                            false, "Cannot delete user with id '" + id + "' since it doesn't exist!"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>("User has been successfully removed!", HttpStatus.OK);
    }

}
