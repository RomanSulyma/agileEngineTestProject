package com.example.agileengine.agileengine.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "photos")
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photos_gen")
    @SequenceGenerator(sequenceName = "photos_seq", name = "photos_gen", allocationSize = 1)
    private Long id;

    private String photoId;

    private String author;

    private String camera;

    private String tags;

    private String cropped_picture;

    private String full_picture;
}
