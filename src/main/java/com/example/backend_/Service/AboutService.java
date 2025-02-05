package com.example.backend_.Service;


import com.example.backend_.entity.About;
import com.example.backend_.repository.AboutRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AboutService {
    private final AboutRepository aboutRepository;

    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public List<About> getAllAboutDetails() {
        return aboutRepository.findAll();
    }

    public Optional<About> getAboutById(UUID id) {
        return aboutRepository.findById(id);
    }

    public About saveAbout(String name, String description, MultipartFile file) throws IOException {
        About about = new About();
        about.setName(name);
        about.setDescription(description);
        about.setImage(file.getBytes());
        return aboutRepository.save(about);
    }
    public About addAbout(String name, String description, MultipartFile image) throws IOException {
        About about = new About();
        about.setName(name);
        about.setDescription(description);
        about.setImage(image.getBytes());  // Convert image to byte array
        return aboutRepository.save(about);
    }


    public About updateAbout(UUID id, String name, String description, MultipartFile file) throws IOException {
        Optional<About> optionalAbout = aboutRepository.findById(id);
        if (optionalAbout.isPresent()) {
            About about = optionalAbout.get();
            about.setName(name);
            about.setDescription(description);
            if (file != null && !file.isEmpty()) {
                about.setImage(file.getBytes());
            }
            return aboutRepository.save(about);
        }
        return null;
    }

    public void deleteAbout(UUID id) {
        aboutRepository.deleteById(id);
    }
}
