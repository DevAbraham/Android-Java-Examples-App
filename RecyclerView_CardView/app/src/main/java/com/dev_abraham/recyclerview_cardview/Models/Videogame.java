package com.dev_abraham.recyclerview_cardview.Models;

public class Videogame {

    public String name;
    public int poster;
    public int icon;
    public int quantity;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Videogame(){

    }
    public Videogame(String name,int poster,int icon,int quantity){
        this.name=name;
        this.poster=poster;
        this.icon = icon;
        this.quantity = quantity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public void addQuantity(){
        this.quantity++;
    }
    public void resetQuatity(){
        this.quantity=0;
    }

}
