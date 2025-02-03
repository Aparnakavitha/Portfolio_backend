package com.example.backend_.Controller;

import com.example.backend_.entity.About;
import com.example.backend_.Service.AboutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/about")
@CrossOrigin(origins = "http://localhost:3000")
public class AboutController {
    private final AboutService aboutService;

    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @GetMapping
    public List<About> getAllAboutDetails() {
        return aboutService.getAllAboutDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<About> getAboutById(@PathVariable UUID id) {
        Optional<About> about = aboutService.getAboutById(id);
        return about.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<About> saveAbout(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("image") MultipartFile file) throws IOException {
        About savedAbout = aboutService.saveAbout(name, description, file);
        return ResponseEntity.ok(savedAbout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<About> updateAbout(@PathVariable UUID id,
                                             @RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        About updatedAbout = aboutService.updateAbout(id, name, description, file);
        return updatedAbout != null ? ResponseEntity.ok(updatedAbout) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbout(@PathVariable UUID id) {
        aboutService.deleteAbout(id);
        return ResponseEntity.noContent().build();
    }
}
