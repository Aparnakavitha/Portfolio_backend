package com.example.backend_.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.example.backend_.Service.LoginService;
import com.example.backend_.entity.Login;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")  // React frontend URL
public class LoginController {

    @Autowired
    private LoginService loginService;

    // User Registration with Hashed Password
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");

        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(password); // Raw password, will be hashed in service

        loginService.saveUser(newUser);

        return Map.of("status", "User created successfully");
    }

    // User Login with Hashed Password Validation
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
}
