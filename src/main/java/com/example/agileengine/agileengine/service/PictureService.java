package com.example.agileengine.agileengine.service;

import com.example.agileengine.agileengine.entity.Photo;
import com.example.agileengine.agileengine.model.DetailedPicture;
import com.example.agileengine.agileengine.model.PhotoResponse;
import com.example.agileengine.agileengine.model.Picture;
import com.example.agileengine.agileengine.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PictureService {

    private final RestTemplatesService restTemplatesService;
    private final PictureRepository pictureRepository;

    /**
     * Get all pictures from API
     * @param token
     * @throws Exception
     */
    public void getPicturesFromAPI(final String token) throws Exception {

        boolean hasNextPage = true;
        long currentPage = 1L;

        while (hasNextPage) {

            final List<DetailedPicture> detailedPictures = new ArrayList<>();

            final PhotoResponse photos =
                    restTemplatesService.getPicturesByPage(currentPage, token);

            final List<Picture> pictures = photos.getPictures();

            for (final Picture picture : pictures) {

                final DetailedPicture detailedPicture =
                        restTemplatesService.getDetailedPicture(token, picture.getId());

                if(detailedPicture != null) {
                    detailedPictures.add(detailedPicture);
                }
            }

            final List<Photo> photoList = detailedPictures.stream()
                    .map(this::mapPictureToEntity)
                    .collect(Collectors.toList());

            pictureRepository.saveAll(photoList);

            hasNextPage = photos.getHasMore();
            currentPage = photos.getPage() + 1;
        }
    }

    /**
     * Get photos from DB
     * @param pictureId
     * @param author
     * @param camera
     * @param tag
     * @return
     */
    public List<Photo> getPhotos(
            final String pictureId,
            final String author,
            final String camera,
            final String tag) {
        return pictureRepository.findPhoto(pictureId, author, camera, tag);
    }

    /**
     * Map model to entity
     * @param picture
     * @return
     */
    private Photo mapPictureToEntity(final DetailedPicture picture) {

        final Photo photo = new Photo();

        photo.setPhotoId(picture.getId());
        photo.setAuthor(picture.getAuthor());
        photo.setCamera(picture.getCamera());
        photo.setTags(picture.getTags());
        photo.setCropped_picture(picture.getCropped_picture());
        photo.setFull_picture(picture.getFull_picture());

        return photo;
    }

    /**
     * Delete photos in DB
     */
    @Transactional
    void deletePhotos() {
        pictureRepository.deleteAll();
    }
}
