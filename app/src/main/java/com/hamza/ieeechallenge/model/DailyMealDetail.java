package com.hamza.ieeechallenge.model;

public class DailyMealDetail {
    String Image;
    String name;
    String restaurant;
    String price;
    String type;


    public DailyMealDetail() {
    }

    public DailyMealDetail(String image, String name, String restaurant, String price) {
        Image = image;
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
