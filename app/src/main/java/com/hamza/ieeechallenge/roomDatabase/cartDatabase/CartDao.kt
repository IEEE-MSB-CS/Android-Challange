package com.hamza.ieeechallenge.roomDatabase.cartDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun readAllData(): LiveData<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cart : Cart)

    @Update
    suspend fun updateCartItem(cart : Cart)

    @Delete
    suspend fun deleteCartItem(cart : Cart)

}