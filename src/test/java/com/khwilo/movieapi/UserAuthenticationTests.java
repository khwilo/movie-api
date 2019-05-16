package com.khwilo.movieapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.khwilo.movieapi.dao.RoleRepository;
import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.model.Role;
import com.khwilo.movieapi.model.RoleName;
import com.khwilo.movieapi.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserAuthenticationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final long ID = 1;

    @Test
    public void test_user_account_creation() {
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

    @Test
    public void givenUserDoesNotExist_whenUserAccountIsCreated_then201IsReceived()
            throws ClientProtocolException, IOException {
        JsonObject user = new JsonObject();
        user.addProperty("firstName", "jane");
        user.addProperty("lastName", "doez");
        user.addProperty("userName", "januq");
        user.addProperty("emailAddress", "jan@gmail.com");
        user.addProperty("password", "#45ercaa12sa");

        String userDetailsJson = user.toString();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new  HttpPost("http://localhost:3000/api/v1/auth/register");
        StringEntity entity = new StringEntity(userDetailsJson);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        JsonObject result = (JsonObject) new JsonParser().parse(EntityUtils.toString(responseEntity));
        assertEquals(response.getStatusLine().getStatusCode(), 201);
        assertEquals(result.get("success").getAsBoolean(), true);
        assertEquals(result.get("message").getAsString(), "You have successfully signed up!");
    }
}
