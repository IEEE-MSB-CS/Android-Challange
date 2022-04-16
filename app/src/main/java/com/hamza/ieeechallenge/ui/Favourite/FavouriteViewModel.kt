package com.hamza.ieeechallenge.ui.Favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.ieeechallenge.repositories.FavouriteRepository
import com.hamza.ieeechallenge.roomDatabase.entities.Favourite
import com.hamza.ieeechallenge.roomDatabase.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    public lateinit var readAllData : LiveData<List<Favourite>>
     var isFavourite : String = "0"
    private val repository : FavouriteRepository

    init {
        val favouriteDao = MyDatabase.getDatabase(application).getFavouriteDo()
        repository = FavouriteRepository(favouriteDao)
        readAllData = repository.readAllData

    }

    fun isFavourite(name : String) : LiveData<String>{
      return repository.isFavourite(name)
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