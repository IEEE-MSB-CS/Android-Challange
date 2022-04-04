package com.hamza.ieeechallenge.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun readAllData(): LiveData<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cart : Cart)
}