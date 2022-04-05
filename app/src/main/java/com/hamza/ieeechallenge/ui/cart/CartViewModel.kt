package com.hamza.ieeechallenge.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.ieeechallenge.model.CartModule
import com.hamza.ieeechallenge.repositories.CartRepository
import com.hamza.ieeechallenge.roomDatabase.Cart
import com.hamza.ieeechallenge.roomDatabase.CartDao
import com.hamza.ieeechallenge.roomDatabase.CartDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    public lateinit var readAllData : LiveData<List<Cart>>
    private val repository : CartRepository

    init {
        val cartDao = CartDatabase.getDatabase(application).getCartDao()
        repository = CartRepository(cartDao)
        readAllData = repository.readAllData
    }

    fun addToCart(cart: Cart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(cart)
        }
    }
}