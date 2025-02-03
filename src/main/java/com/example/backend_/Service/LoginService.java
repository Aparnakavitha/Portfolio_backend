package com.example.backend_.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend_.repository.LoginRepository;
import com.example.backend_.entity.Login;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    public boolean validateUser(String username, String password) {
        Optional<Login> user = loginRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    public Login saveUser(Login user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Hash password before saving
        return loginRepository.save(user);
    }
}
