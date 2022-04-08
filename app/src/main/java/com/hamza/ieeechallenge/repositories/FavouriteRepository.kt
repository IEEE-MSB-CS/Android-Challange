package com.hamza.ieeechallenge.repositories

import androidx.lifecycle.LiveData
import com.hamza.ieeechallenge.roomDatabase.Favourite
import com.hamza.ieeechallenge.roomDatabase.FavouriteDao
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart

class FavouriteRepository(private val favouriteDao: FavouriteDao) {

    val readAllData : LiveData<List<Favourite>> = favouriteDao.readAllData()
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