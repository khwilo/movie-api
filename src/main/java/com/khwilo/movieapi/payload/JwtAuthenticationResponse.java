package com.khwilo.movieapi.payload;

import com.khwilo.movieapi.constants.SecurityConstants;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenPrefix = SecurityConstants.TOKEN_PREFIX;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
}
