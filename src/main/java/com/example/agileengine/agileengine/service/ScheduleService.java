package com.example.agileengine.agileengine.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class ScheduleService {

    private final PictureService pictureService;
    private final AuthorizationService authorizationService;

    /**
     * Update photos by schedule
     */
    @Scheduled(fixedDelayString = "${photo.reload.delay}")
    public void updatePhotosBySchedule() {

        try {
            final String token = authorizationService.authorize();

            pictureService.deletePhotos();
            pictureService.getPicturesFromAPI(token);

        } catch (Exception e) {
            log.error("Error while updating");
        }
    }

}
