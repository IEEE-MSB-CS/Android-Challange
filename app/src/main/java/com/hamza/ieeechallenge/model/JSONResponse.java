package com.hamza.ieeechallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONResponse {
    Food[] pizza;
    Food[] ice_cream;
    Food[] burger;
    Food[] salad;
    Food[] sandwich;


    public Food[] getPizza() {
        return pizza;
    }

    public void setPizza(Food[] pizza) {
        this.pizza = pizza;
    }

    public Food[] getIce_cream() {
        return ice_cream;
    }

    public void setIce_cream(Food[] ice_cream) {
        this.ice_cream = ice_cream;
    }

    public Food[] getBurger() {
        return burger;
    }

    public void setBurger(Food[] burger) {
        this.burger = burger;
    }

    public Food[] getSalad() {
        return salad;
    }

    public void setSalad(Food[] salad) {
        this.salad = salad;
    }

    public Food[] getSandwich() {
        return sandwich;
    }

    public void setSandwich(Food[] sandwich) {
        this.sandwich = sandwich;
    }
}
