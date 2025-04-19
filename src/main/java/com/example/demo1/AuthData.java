package com.example.demo1;

import java.util.List; // ✅ Add this
import java.util.ArrayList; // Optional, only if needed

public class AuthData {
    public static List<User> getUsers() {
        return List.of(
                new User("admin", "admin123", "Admin"),
                new User("employee", "emp123", "Employee")
        );
    }

    public static User authenticate(String username, String password) {
        return getUsers().stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}
