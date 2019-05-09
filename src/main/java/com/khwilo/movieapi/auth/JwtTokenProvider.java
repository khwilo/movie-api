package com.khwilo.movieapi.auth;

import com.khwilo.movieapi.constants.SecurityConstants;
import com.khwilo.movieapi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecretKey}")
    private String jwtSecretKey;

    public String generateToken(Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        byte[] signingKey = SecurityConstants.JWT_SECRET_KEY.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS256)
                .setHeaderParam("type", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();

        return token;

    }
}
