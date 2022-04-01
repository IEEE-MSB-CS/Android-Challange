package com.hamza.ieeechallenge.ROOM;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyCartDao {

    @Insert
    public void addToCart(myCartRoom cart);

    @Query("SELECT * FROM MyCart")
    public List<myCartRoom> getData();

    @Query("SELECT EXISTS (SELECT 1 FROM mycart WHERE id=:id)")
    public int isAddToCart(int id);

    @Query("select COUNT (*) from MyCart")
    int countCart();

    @Query("DELETE FROM MyCart WHERE id=:id ")
    int deleteItem(int id);
}
