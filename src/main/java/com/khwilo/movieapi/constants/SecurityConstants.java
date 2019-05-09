package com.khwilo.movieapi.constants;


import org.springframework.beans.factory.annotation.Value;

public final class SecurityConstants {

    public static final String AUTH_SIGNUP_URL = "/auth/register";
    public static final String AUTH_LOGIN_URL = "/auth/login";
    public static final String MOVIE_URL = "/api/movies";
    public static final String MOVIE_URL_ID_PATH = "/api/movies/{\\d+}";


    public static final String JWT_SECRET_KEY = "YTI1NGNmNmYwMTE3OTQ2MWZkYmMwNzUw";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "movie-api";
    public static final String TOKEN_AUDIENCE = "movie-app";
}
