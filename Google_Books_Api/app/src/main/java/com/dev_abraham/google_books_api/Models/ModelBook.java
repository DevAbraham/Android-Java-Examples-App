package com.dev_abraham.google_books_api.Models;

public class ModelBook {

   String title;
   String [] authors;
   String [] categories;
   String publisher;
   String publishedDate;
   ModelISBN [] industryIdentifiers;
   ModelThumbnail imageLinks;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ModelISBN[] getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(ModelISBN[] industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ModelThumbnail getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ModelThumbnail imageLinks) {
        this.imageLinks = imageLinks;
    }
}
