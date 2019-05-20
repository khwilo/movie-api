package com.khwilo.movieapi.utility;

import com.google.gson.JsonObject;

public class SampleData {

    public static String createUser(
            String firstName, String lastName, String userName, String emailAddress, String password) {
        JsonObject user = new JsonObject();
        user.addProperty("firstName", firstName);
        user.addProperty("lastName", lastName);
        user.addProperty("userName", userName);
        user.addProperty("emailAddress", emailAddress);
        user.addProperty("password", password);

        return user.toString();
    }

    public static String loginUser(String usernameOrEmail, String password) {
        JsonObject user = new JsonObject();
        user.addProperty("usernameOrEmail", usernameOrEmail);
        user.addProperty("password", password);

        return user.toString();
    }
}
