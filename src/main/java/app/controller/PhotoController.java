package app.controller;

import app.consts.ExceptionMessages;
import app.dto.Photo;
import app.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("/user/{userId}")
    public Iterable<Photo> getUserPhotos(@PathVariable Long userId) {
        if (userId < 1) throw new IllegalArgumentException(ExceptionMessages.ID_SHOULD_NOT_BE_ZERO_OR_NEGATIVE);
        return photoRepository.findPhotosByUserId(userId);
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public Photo addPhoto(@RequestBody Photo photo) {
        if (photo.getPath() == null || !photo.getPath().equals(""))
            throw new IllegalArgumentException(ExceptionMessages.PATH_SHOULD_NOT_BE_NULL_OR_EMPTY);
        if (photo.getUser().getId() == null)
            throw new IllegalArgumentException(ExceptionMessages.ID_SHOULD_NOT_BE_ZERO_OR_NEGATIVE);
        photoRepository.save(photo);
        return photo;
    }
}