package com.example.pulsepod;

public class Book {
    private String title;
    private String author;
    private int imageResId; // book cover from drawable

    public Book(String title, String author, int imageResId) {
        this.title = title;
        this.author = author;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getImageResId() {
        return imageResId;
    }
}
