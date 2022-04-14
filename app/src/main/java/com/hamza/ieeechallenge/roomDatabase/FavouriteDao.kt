package com.hamza.ieeechallenge.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourites_table")
    fun readAllData(): LiveData<List<Favourite>>

    @Query("SELECT isFavourite FROM favourites_table WHERE title =:name")
    fun getIsFavourite(name : String) : LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(favoriteItem : Favourite)

    @Update
    suspend fun updateFavouriteItem(favoriteItem : Favourite)

    @Delete
    suspend fun deleteFavouriteItem(favoriteItem : Favourite)

}