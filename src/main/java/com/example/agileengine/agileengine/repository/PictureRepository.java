package com.example.agileengine.agileengine.repository;

import com.example.agileengine.agileengine.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Photo, Long> {

    @Query("FROM Photo WHERE photoId=:photoId OR author=:author OR camera=:camera OR tags like %:tag%")
    List<Photo> findPhoto(final String photoId, final String author, final String camera, final String tag);

}
