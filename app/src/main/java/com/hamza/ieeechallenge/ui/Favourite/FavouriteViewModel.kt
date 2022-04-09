package com.hamza.ieeechallenge.ui.Favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.ieeechallenge.repositories.FavouriteRepository
import com.hamza.ieeechallenge.roomDatabase.Favourite
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    public lateinit var readAllData : LiveData<List<Favourite>>
    private val repository : FavouriteRepository

    init {
        val favouriteDao = MyDatabase.getDatabase(application).getFavouriteDo()
        repository = FavouriteRepository(favouriteDao)
        readAllData = repository.readAllData
    }

    fun addToFavourite(favouriteItem : Favourite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavourite(favouriteItem)
        }
    }

    fun updateFavouriteItem(favouriteItem : Favourite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavouriteItem(favouriteItem)
        }
    }

    fun deleteFavouriteItem(favouriteItem : Favourite){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavouriteItem(favouriteItem)
        }
    }

}