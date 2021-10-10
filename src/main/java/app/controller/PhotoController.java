package app.controller;

import app.dto.Photo;
import app.repo.PhotoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private PhotoRepository photoRepository;

    @GetMapping("/user/{userId}")
    public Iterable<Photo> getUserPhotos(@PathVariable Long userId) {
        return List.of(null);

    }
}