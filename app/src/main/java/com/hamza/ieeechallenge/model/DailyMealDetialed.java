package com.hamza.ieeechallenge.model;

public class DailyMealDetialed {
    int Image;
    String name;
    String description;
    String price;
    String type;


    public DailyMealDetialed() {
    }

    public DailyMealDetialed(int image, String name, String description, String price) {
        Image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
