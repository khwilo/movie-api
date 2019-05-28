package com.khwilo.movieapi.utility;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class APIRequest {

    CloseableHttpClient client;

    public APIRequest() {
        this.client = HttpClients.createDefault();
    }

    public CloseableHttpResponse signUp(String signUpUrl, String userRegistration)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(userRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");

        return this.client.execute(signUpPost);
    }

    public CloseableHttpResponse login(
            String signUpUrl, String userRegistration, String loginUrl, String userLogin)
            throws ClientProtocolException, IOException {
        HttpPost signUpPost = new HttpPost(signUpUrl);
        StringEntity signUpEntity = new StringEntity(userRegistration);
        signUpPost.setEntity(signUpEntity);
        signUpPost.setHeader("Accept", "application/json");
        signUpPost.setHeader("Content-type", "application/json");
        this.client.execute(signUpPost);

        HttpPost loginPost = new HttpPost(loginUrl);
        StringEntity loginEntity = new StringEntity(userLogin);
        loginPost.setEntity(loginEntity);
        loginPost.setHeader("Accept", "application/json");
        loginPost.setHeader("Content-type", "application/json");

        return this.client.execute(loginPost);
    }
}
