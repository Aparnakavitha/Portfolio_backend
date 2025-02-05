package com.example.backend_.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.example.backend_.Service.LoginService;
import com.example.backend_.entity.Login;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend access
public class LoginController {

    @Autowired
    private LoginService loginService;

    // ✅ Register a new user with hashed password
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");

        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(password); // Will be hashed in service

        loginService.saveUser(newUser);

        return Map.of("status", "User created successfully");
    }

    // ✅ User Login with password validation
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        boolean isValid = loginService.validateUser(username, password);

        if (isValid) {
            return Map.of("status", "success");
        } else {
            return Map.of("status", "failure");
        }
    }

    // ✅ Fetch all registered users
    @GetMapping("/users")
    public List<Login> getAllUsers() {
        return loginService.getAllUsers();
    }

    // ✅ Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteUser(@PathVariable UUID id) {
        boolean deleted = loginService.deleteUser(id);
        if (deleted) {
            return Map.of("status", "User deleted successfully");
        } else {
            return Map.of("status", "User not found");
        }
    }
}
