package com.example.myapplication;

public class ItemModels {
    private int position;
    private int id;
    private String image;
    private String title;
    private String author;
    private String description;

    public ItemModels( int id, String image, String title, String author, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
