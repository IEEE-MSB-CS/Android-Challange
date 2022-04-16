package com.hamza.ieeechallenge.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamza.ieeechallenge.roomDatabase.entities.Cart
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite

@Database(entities = [Cart::class , Favourite::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

        abstract fun getCartDao(): CartDao
        abstract fun getFavouriteDo() : FavouriteDao

        companion object {
            @Volatile
            private var INSTANCE: MyDatabase? = null

            fun getDatabase(context: Context): MyDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "mdatabase"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }