package com.khwilo.movieapi.security;

import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserNameOrEmailAddress(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User with username / email not found: " + usernameOrEmail
                        )
                );
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with the id '" + id + "' not found")
        );
        return UserPrincipal.create(user);
    }
}
