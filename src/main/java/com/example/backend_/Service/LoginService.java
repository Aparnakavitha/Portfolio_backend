package com.example.backend_.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend_.repository.LoginRepository;
import com.example.backend_.entity.Login;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Validate user login
    public boolean validateUser(String username, String password) {
        Optional<Login> user = loginRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    // ✅ Save user with hashed password
    public Login saveUser(Login user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash password before saving
        return loginRepository.save(user);
    }

    // ✅ Get all users
    public List<Login> getAllUsers() {
        return loginRepository.findAll();
    }

    // ✅ Delete user by ID
    public boolean deleteUser(UUID id) {
        if (loginRepository.existsById(id)) {
            loginRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
