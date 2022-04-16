package com.hamza.ieeechallenge.roomDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "cart" )
data class Cart (
    @PrimaryKey(autoGenerate = true)
    var orderId : Int ,
    @ColumnInfo
    var userId : String ,
    @ColumnInfo
    var status : String ,
    @ColumnInfo
    var title : String ,
    @ColumnInfo
    var image : String,
    @ColumnInfo
    var restaurantName : String,
    @ColumnInfo
    var quantity : Int,
    @ColumnInfo
    var price : Double
    )