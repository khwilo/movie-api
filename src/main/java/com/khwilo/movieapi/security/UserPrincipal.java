package com.khwilo.movieapi.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khwilo.movieapi.model.Role;
import com.khwilo.movieapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;

    @JsonIgnore
    private String emailAddress;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(
            Long id, String firstName, String lastName, String userName,
            String emailAddress, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        Collection<Role> userRoles = null;

        if (user != null) {
            userRoles = user.getRoles();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        return new UserPrincipal(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmailAddress(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
