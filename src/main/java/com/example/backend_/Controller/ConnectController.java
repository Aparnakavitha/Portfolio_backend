package com.example.backend_.Controller;

import com.example.backend_.entity.ConnectForm;
import com.example.backend_.repository.ConnectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ConnectController {

    @Autowired
    private ConnectRepository repository;

    // POST endpoint: Save form data (this will receive form data from React frontend)
    @PostMapping("/connect")
    public ResponseEntity<String> saveForm(@RequestBody ConnectForm form) {
        repository.save(form);
        return ResponseEntity.ok("Form submitted successfully!");
    }

    // GET endpoint: Fetch the most recently submitted form data
    @GetMapping("/connect")
    public ResponseEntity<ConnectForm> getConnectData() {
        // Retrieve the last submitted form from the database
        Optional<ConnectForm> latestForm = repository.findTopByOrderByIdDesc();

        return latestForm
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
