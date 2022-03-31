package com.hamza.ieeechallenge.ROOM;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {myCartRoom.class},version = 1 , exportSchema = true)
public abstract class DataBase extends RoomDatabase {
    public abstract MyCartDao cartDao();
}
