package com.hamza.ieeechallenge.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
class Favourite (
    @PrimaryKey(autoGenerate = true)
    var id : Int ,
    var title : String,
    var restaurantName : String,
    var rating : Double ,
    var price : String ,
    var description : String ,
    var image : String
)