package com.hamza.ieeechallenge.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourite_table")
    fun readAllData(): LiveData<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(favoriteItem : Favourite)

    @Update
    suspend fun updateFavouriteItem(favoriteItem : Favourite)

    @Delete
    suspend fun deleteFavouriteItem(favoriteItem : Favourite)
}