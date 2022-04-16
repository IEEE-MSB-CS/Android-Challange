package com.hamza.ieeechallenge.model;

import com.hamza.ieeechallenge.roomDatabase.entities.Cart;

import java.util.List;

public class Food {
    int id;
    String title;
    String restaurant;
    double rating;
    String price;
    String description;
    String image;
    String status;
    List<Cart>cartList;

    public Food() {
    }

    public Food(int id, String title, String restaurant, double rating, String price, String description, String image) {
        this.id = id;
        this.title = title;
        this.restaurant = restaurant;
        this.rating = rating;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Food(String title, String restaurant, String price, String image, String status) {
        this.title = title;
        this.restaurant = restaurant;
        this.price = price;
        this.image = image;
        this.status = status;
    }

    public Food(String title, String restaurant, String price, String image, String status, List<Cart> cartList) {
        this.title = title;
        this.restaurant = restaurant;
        this.price = price;
        this.image = image;
        this.status = status;
        this.cartList = cartList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
