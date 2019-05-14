package com.khwilo.movieapi;

import com.khwilo.movieapi.dao.RoleRepository;
import com.khwilo.movieapi.model.Role;
import com.khwilo.movieapi.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieApiApplication implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role roleUser = new Role(RoleName.ROLE_USER);
        Role adminUser = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(roleUser);
        roleRepository.save(adminUser);
    }
}
