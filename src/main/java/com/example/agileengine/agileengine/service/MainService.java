package com.example.agileengine.agileengine.service;

import com.example.agileengine.agileengine.entity.Photo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class MainService {

    private final PictureService pictureService;
    private final Scanner scanner = new Scanner(System.in);

    public void appMenu() {

        boolean trigger = true;

        while(trigger) {

            System.out.println("Enter params (photoId, author, camera, tag)");

            System.out.println("Enter photoId:");
            final String photoId = scanner.nextLine();

            System.out.println("Enter author:");
            final String author = scanner.nextLine();

            System.out.println("Enter camera:");
            final String camera = scanner.nextLine();

            System.out.println("Enter tag:");
            final String tag = scanner.nextLine();

            List<Photo> photos =
                    pictureService.getPhotos(photoId, author, camera, tag);

            photos.forEach(photo -> System.out.println(photo.toString()));

            System.out.println("Another search? (y/n)");
            final String anotherSearch = scanner.nextLine();

            if (anotherSearch.equals("n")) {
             trigger = false;
            }
        }

        scanner.close();
    }
}
