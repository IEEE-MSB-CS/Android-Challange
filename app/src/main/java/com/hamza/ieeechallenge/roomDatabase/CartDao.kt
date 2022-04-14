package com.hamza.ieeechallenge.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamza.ieeechallenge.roomDatabase.entities.Cart

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun readAllData(): LiveData<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart : Cart)

    @Update
    suspend fun updateCartItem(cart : Cart)

    @Delete
    suspend fun deleteCartItem(cart : Cart)

}