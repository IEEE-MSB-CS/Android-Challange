package com.hamza.ieeechallenge.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

        abstract fun getCartDao(): CartDao

        companion object {
            @Volatile
            private var INSTANCE: CartDatabase? = null

            fun getDatabase(context: Context): CartDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDatabase::class.java,
                        "cart"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }