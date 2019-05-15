package com.khwilo.movieapi;

import com.khwilo.movieapi.dao.RoleRepository;
import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.model.Role;
import com.khwilo.movieapi.model.RoleName;
import com.khwilo.movieapi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserAuthenticationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final long ID = 1;

    @Test
    public void test_user_account_registration() {
        User user = new User(
                "john", "doe", "baku", "doe@example.com", "abcd123"
        );
        user.setPassword(user.getPassword());
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).get();
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
        User found = userRepository.findById(ID).get();
        assertEquals("john", found.getFirstName());
    }
}
