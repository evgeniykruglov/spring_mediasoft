package app.controller;

import app.dto.Photo;
import app.repo.PhotoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private PhotoRepository photoRepository;

    @GetMapping("/user/{userId}")
    public Iterable<Photo> getUserPhotos(@PathVariable Long userId) {
        return photoRepository.findPhotosByUserId(userId);
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public Photo addPhoto(@RequestBody Photo photo) {
        photoRepository.save(photo);
        return photo;
    }
}