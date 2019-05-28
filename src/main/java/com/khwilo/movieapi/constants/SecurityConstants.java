package com.khwilo.movieapi.constants;

public final class SecurityConstants {
    public static final String AUTH_URL = "/api/v1/auth/**";
    public static final String ACTUATOR = "/actuator/**";
    public static final String H2_CONSOLE = "/h2Console/**";


    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
