package com.example.android.yipyapnewjava;

/**
 * Created by Uddin on 12/4/2017.
 * this is the post class (PostMalone) that I have created
 * it has setters and getters for the titles, description and pictures
 */

public class PostMalone {
    private String title;
    private String description;
    private String image;

    public PostMalone(){}

    public PostMalone(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
