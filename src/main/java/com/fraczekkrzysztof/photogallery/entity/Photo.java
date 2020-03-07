package com.fraczekkrzysztof.photogallery.entity;

import javax.persistence.*;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String photoName;

    @ManyToOne(optional = false)
    private Gallery gallery;

    public Photo() {
    }

    public Photo(long id, String photoName, Gallery gallery) {
        this.id = id;
        this.photoName = photoName;
        this.gallery = gallery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
