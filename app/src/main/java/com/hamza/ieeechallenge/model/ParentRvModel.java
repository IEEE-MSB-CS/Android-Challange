package com.hamza.ieeechallenge.model;

import com.hamza.ieeechallenge.roomDatabase.entities.Cart;

import java.util.List;

public class ParentRvModel {
    private String title;
    private List<Cart> foodList;

    public ParentRvModel(String title, List<Cart> foodList) {
        this.title = title;
        this.foodList = foodList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cart> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Cart> foodList) {
        this.foodList = foodList;
    }
}
