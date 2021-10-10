package app.repo;


import app.dto.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p from Photo p where p.user = ?1")
    List<Photo> findPhotosByUserId(Long userId);
}
