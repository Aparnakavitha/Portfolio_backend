package com.example.backend_.Controller;

import com.example.backend_.Service.SkillService;
import com.example.backend_.entity.SkillEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Retrieve all skills
    @GetMapping
    public ResponseEntity<List<SkillEntity>> getSkills() {
        List<SkillEntity> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    // Create a new skill
    @PostMapping
    public ResponseEntity<SkillEntity> createSkill(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile image) {
        try {
            SkillEntity skill = new SkillEntity();
            skill.setName(name);
            skill.setImage(image.getBytes());
            SkillEntity savedSkill = skillService.saveSkill(skill);
            return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Retrieve a skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<SkillEntity> getSkill(@PathVariable UUID id) {
        SkillEntity skill = skillService.getSkillById(id);
        return skill != null ? ResponseEntity.ok(skill) : ResponseEntity.notFound().build();
    }

    // Update a skill by ID
    @PutMapping("/{id}")
    public ResponseEntity<SkillEntity> updateSkill(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            SkillEntity updatedSkill = skillService.updateSkill(id, name, image);
            return updatedSkill != null ? ResponseEntity.ok(updatedSkill) : ResponseEntity.notFound().build();
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a skill by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable UUID id) {
        boolean deleted = skillService.deleteSkill(id);
        if (deleted) {
            return new ResponseEntity<>("Skill deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }
    }
}
