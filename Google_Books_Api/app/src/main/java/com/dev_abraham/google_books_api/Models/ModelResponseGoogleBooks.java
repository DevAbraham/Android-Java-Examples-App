package com.dev_abraham.google_books_api.Models;

import java.util.ArrayList;

public class ModelResponseGoogleBooks {

    int totalItems;
    ArrayList<ModelItems> items;


    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<ModelItems> getItems() {
        return items;
    }

    public void setItems(ArrayList<ModelItems> items) {
        this.items = items;
    }
}
