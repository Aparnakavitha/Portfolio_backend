package com.example.backend_.Controller;

import com.example.backend_.entity.Project;
import com.example.backend_.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a new project
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProject(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            projectService.addProject(name, image, description);
            return ResponseEntity.ok("Project added successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error saving project: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProject(
            @PathVariable UUID id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Project updatedProject = projectService.updateProject(id, name, image, description);
            if (updatedProject != null) {
                return ResponseEntity.ok("Project updated successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error updating project: " + e.getMessage());
        }
    }



    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID id) {
        boolean isDeleted = projectService.deleteProject(id);
        if (isDeleted) {
            return ResponseEntity.ok("Project deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // Get project image
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProjectImage(@PathVariable UUID id) {
        Project project = projectService.getProjectById(id);
        if (project != null && project.getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust MIME type if needed (e.g., IMAGE_PNG)
                    .body(project.getImage());
        }
        return ResponseEntity.notFound().build();
    }





}
