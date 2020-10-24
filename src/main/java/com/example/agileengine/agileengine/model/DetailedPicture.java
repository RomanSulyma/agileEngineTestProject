package com.example.agileengine.agileengine.model;

import lombok.Data;

@Data
public class DetailedPicture {

    private String id;
    private String author;
    private String camera;
    private String tags;
    private String cropped_picture;
    private String full_picture;
}
