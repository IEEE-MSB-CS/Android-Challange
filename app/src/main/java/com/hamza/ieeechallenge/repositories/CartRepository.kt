package com.hamza.ieeechallenge.repositories

import androidx.lifecycle.LiveData
import com.hamza.ieeechallenge.roomDatabase.Cart
import com.hamza.ieeechallenge.roomDatabase.CartDao

class CartRepository(private val cartDao : CartDao) {
    val readAllData : LiveData<List<Cart>> = cartDao.readAllData()
    suspend fun addToCart(cart : Cart){
        cartDao.addToCart(cart)
    }
}