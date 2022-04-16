package com.hamza.ieeechallenge.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hamza.ieeechallenge.repositories.CartRepository
import com.hamza.ieeechallenge.roomDatabase.entities.Cart
import com.hamza.ieeechallenge.roomDatabase.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    public lateinit var readAllData : LiveData<List<Cart>>
    private val repository : CartRepository

    init {
        val cartDao = MyDatabase.getDatabase(application).getCartDao()
        repository = CartRepository(cartDao)
        readAllData = repository.readAllData
    }

    fun addToCart(cart: Cart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(cart)
        }
    }

    fun updateCartItem(cart : Cart){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCartItem(cart)
        }
    }

    fun deleteCartItem(cart : Cart){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCartItem(cart)
        }
    }
}