package com.hamza.ieeechallenge.model;

public class DailyMeal {
    int image ;
    String name;
    String description;
    String type;

    public DailyMeal(int image, String name, String description , String type) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public DailyMeal() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
}
