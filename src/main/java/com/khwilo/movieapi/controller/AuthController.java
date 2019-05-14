package com.khwilo.movieapi.controller;

import com.khwilo.movieapi.dao.RoleRepository;
import com.khwilo.movieapi.exception.AppException;
import com.khwilo.movieapi.model.Role;
import com.khwilo.movieapi.model.RoleName;
import com.khwilo.movieapi.payload.ApiResponse;
import com.khwilo.movieapi.payload.JwtAuthenticationResponse;
import com.khwilo.movieapi.payload.LoginRequest;
import com.khwilo.movieapi.payload.SignUpRequest;
import com.khwilo.movieapi.security.JwtTokenProvider;
import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.model.User;
import com.khwilo.movieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userRepository.existsByEmailAddress(signUpRequest.getEmailAddress())) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Email address is already in use!"),
                    HttpStatus.BAD_REQUEST
            );
        }

        User user = new User(
                signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getUserName(), signUpRequest.getEmailAddress(), signUpRequest.getPassword()
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User role not set")
                );
        user.setRoles(Collections.singleton(userRole));

        userService.save(user);

        return new ResponseEntity<>(
                new ApiResponse(true, "You have successfully signed up!"),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }
}
