package com.hamza.ieeechallenge.model;

public class CartModule {
    String OrderId;
    String userId;
    String image;
    String name;
    String price;
    int id;


    public CartModule(String image, String name, String price, int id) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public CartModule() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CartModule(String image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public CartModule(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CartModule(String orderId) {
        OrderId = orderId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
