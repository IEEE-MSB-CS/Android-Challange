package com.hamza.ieeechallenge.repositories

import androidx.lifecycle.LiveData
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.Cart
import com.hamza.ieeechallenge.roomDatabase.cartDatabase.CartDao

class CartRepository(private val cartDao : CartDao) {
    val readAllData : LiveData<List<Cart>> = cartDao.readAllData()
    suspend fun addToCart(cart : Cart){
        cartDao.addToCart(cart)
    }

    suspend fun updateCartItem(cart : Cart){
        cartDao.updateCartItem(cart)
    }
    suspend fun deleteCartItem(cart: Cart){
        cartDao.deleteCartItem(cart)
    }
}