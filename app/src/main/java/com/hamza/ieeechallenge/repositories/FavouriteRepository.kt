package com.hamza.ieeechallenge.repositories

import androidx.lifecycle.LiveData
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite
import com.hamza.ieeechallenge.roomDatabase.FavouriteDao

class FavouriteRepository(private val favouriteDao: FavouriteDao) {

    val readAllData : LiveData<List<Favourite>> = favouriteDao.readAllData()

     fun isFavourite(name : String) : LiveData<String>{
        return favouriteDao.getIsFavourite(name)
    }

    suspend fun addToFavourite(favouriteItem : Favourite){
        favouriteDao.addToFavourite(favouriteItem)
    }

    suspend fun updateFavouriteItem(favouriteItem : Favourite){
        favouriteDao.updateFavouriteItem(favouriteItem)
    }
    suspend fun deleteFavouriteItem(favouriteItem : Favourite){
        favouriteDao.deleteFavouriteItem(favouriteItem)
    }
}